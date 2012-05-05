var MarketListModel = Backbone.Model.extend({
    initialize: function() {

    }
});

var MarketListView = Backbone.View.extend({

    events: {
        "click .header .refresh": "_handleRefresh",
        "click .runner": "_handleRunnerClick"
    },

    initialize: function() {
    },

    _handleRefresh: function(event) {
        console.log('refresh');
        var refreshButton = $(event.target)
        refreshButton.attr("disabled", true);

        $.ajax({url: "/rest/betfair/market.json",
                type: "GET",
                dataType: "html",
                cache: false
            })
            .done(function(data) {
                console.log($(data).find('div.content'));
                $('div.content','#market-list').html($(data).find('div.content').html());
            refreshButton.attr("disabled", false);
//                that._hideRefreshOverlay();
            })
            .fail(function() {
//                console.log('failed');
//                // todo - display error
//                that._showNoBetsNotification();
            }).always(function(){
//                that.model.set({refresh: false, refreshing: false}, {silent: true});
            });

    },

    _handleRunnerClick: function(event) {
        if(betSlipModel) {
            var $currentTarget = $(event.currentTarget);
            betSlipModel.update(
                $currentTarget.find(".runnerName").text(),
                $currentTarget.parent("ol").attr("data-market-name"),
                $currentTarget.attr("data-market-id"),
                $currentTarget.attr("data-selection-id"),
                $currentTarget.find(".price").text(),
                "2.0"
            );
        }
    }
});

var marketListModel = new MarketListModel();

new MarketListView({
    model: marketListModel,
    el: $("#market-list")
});