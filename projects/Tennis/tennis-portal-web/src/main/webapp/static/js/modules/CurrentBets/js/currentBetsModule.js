var clazz = clazz || {};

// refreshing
clazz.CurrentBetsModel = Backbone.Model.extend( {
    defaults: {
        refreshing: false
    }
})

clazz.CurrentBetsView = Backbone.View.extend({
    initialize: function() {
        _.bindAll(this, "_cancelUnmatchedBet", "_hideRefreshOverlay", "_showNoBetsNotification", "_showRefreshOverlay");
        this.model.on("change:refresh", this._handleRefresh, this)
    },

    events: {
        "click .bet": "_cancelUnmatchedBet",
        "click .header .refresh": "_handleRefresh"
    },

    _cancelUnmatchedBet: function(event) {
        var that = this,
                $currentTarget = $(event.currentTarget),
                $betOverlay = $currentTarget.find('.overlay'),
                $betElements = $currentTarget.find('.p1');

        // send cancel bet request
        if ("U" === $currentTarget.attr("data-bet-status")) {

            this._showCancellationInProgressOverlay($betElements, $betOverlay);

            var request = $.ajax({
                url: "/betfair/bet/cancel",
                type: "POST",
                dataType: "json",
                data: {betId: $currentTarget.attr("data-bet-id")},
                processData: true,
                cache: false
            });

            request.done(function(data) {
                if (data.status && data.status === "OK") {
                    $currentTarget.remove();
                }
                else {
                    that._handleCancellationError($betElements, $betOverlay, data.status);
                }
            });

            request.fail(function() {
                that._handleCancellationError($betElements, $betOverlay, "ERROR");
            });
        }
    },

    _handleCancellationError: function($betElements, $betOverlay, errorCode) {
        $betOverlay.addClass("error");
        $betOverlay.text("Failed to cancel the bet. Reason: " + errorCode);
        setTimeout(function() {
            $betOverlay.hide();
            $betElements.show();
            $betOverlay.removeClass("error");
        }, 3000);
    },

    _showCancellationInProgressOverlay: function($betElements, $betOverlay) {
        $betOverlay.html($("#betOverlayTmp").html());
        $betOverlay.show();
    },

    _handleRefresh: function() {
        if(this.model.get("refreshing") === false) {
            this.model.set({refreshing: true});

            this._showRefreshOverlay();
            var that = this;

            $.ajax({url: "/betfair/bet",
                type: "GET",
                dataType: "html",
                cache: false
            })
            .done(function(data) {
                $('div.content','#current-bets').html($(data).find('div.content').html());
                that._hideRefreshOverlay();
            })
            .fail(function() {
                console.log('failed');
                // todo - display error
                that._showNoBetsNotification();
            }).always(function(){
                that.model.set({refresh: false, refreshing: false});
            });
        }

    },

    _updateBets: function() {
        this._hideRefreshOverlay();
    },

    _showRefreshOverlay: function() {
        this.$el.find(".overlay").show();
    },

    _showNoBetsNotification: function() {
        // todo - not used probably
        this.$el.find(".betsWrap").empty();
        this.$el.find(".notification").show();
        this._hideRefreshOverlay();
    },

    _hideRefreshOverlay: function() {
        this.$el.find("#refreshOverlay").hide();
    }
});

var currentBetsModel = new clazz.CurrentBetsModel();

new clazz.CurrentBetsView({
    model: currentBetsModel,
    el: $("#current-bets")
});

$("body").bind("bet:placed", function(event, data) {
    currentBetsModel.set({refresh: true});
})