var BetSlipModel = Backbone.Model.extend({
    defaults: {
        marketId: "0",
        selectionId: "0",
        price: "1.0",
        stake: "2.0",

        messages: {
            PLACING: "Placing your bet please wait ...",
            MATCHED: "The bet has been placed and is fully matched",
            PART_MATCHED: "The bet has been placed and partially matched",
            UNMATCHED: "The bet has been placed but is unmatched",
            FAILED: "Failed to place the bet"
        }
    },

    update: function(marketId, selectionId, price, stake) {
        this.set({
            marketId: marketId,
            selectionId: selectionId,
            price: price,
            stake: stake
        });
    }
});

var BetSlipView = Backbone.View.extend({
    initialize: function() {
        _.bindAll(this, "_showOverlay", "_hideOverlay");
        this.model.on("change", this._updateFormData, this);
    },

    events: {
        "submit form": "_handlePlaceBet",
        "click .overlay button": "_hideOverlay"
    },

    _updateFormData: function() {
        this._hideNoSelectionOverlay();

        this.$el.find("h2").text(this.model.get("selectionName"));
        this.$el.find("h3").text(this.model.get("marketFullName"));
        this.$el.find("input[name='marketId']").val(this.model.get("marketId"));
        this.$el.find("input[name='selectionId']").val(this.model.get("selectionId"));
        this.$el.find("input[name='price']").val(this.model.get("price"));
        this.$el.find("input[name='stake']").val(this.model.get("stake"));
    },

    _handlePlaceBet: function(event) {
        if(event.stopPropagation) event.stopPropagation();
        event.preventDefault();

        this._showOverlay("PLACING", true);
        var that = this;
        $.ajax({
            url: "/betfair/bet",
            type: "POST",
            dataType: "json",
            data: {
                marketId: this.$el.find("input[name='marketId']").val(),
                selectionId: this.$el.find("input[name='selectionId']").val(),
                price: this.$el.find("input[name='price']").val(),
                stake: this.$el.find("input[name='stake']").val()
            },
            processData: true,
            cache: false
        })
        .done(function(data){
            var message = data.status;
            if("OK" === data.status) {
                message = data.bet.betState;
                $("body").trigger("bet:placed");
            }
            that._showOverlay(message);
        })
        .fail(function(){
            that._showOverlay("FAILED");
        });
    },

    _showOverlay: function(code, spinner) {
        this.$el.find(".overlay").show();

        var message = this.model.get("messages")[code];
        if(message === undefined) {
            message = code;
        }
        // show spinner
        if(spinner && spinner === true) {
            this.$el.find(".overlay em").addClass("spinner");
        }
        else {
            this.$el.find(".overlay em").removeClass("spinner");
        }

        // show message
        this.$el.find(".overlay em").html(message);
    },

    _hideOverlay: function() {
        this.$el.find(".overlay").hide();
        this.$el.find(".overlay em").html("");
    },

    _hideNoSelectionOverlay: function() {
        this.$el.find(".emptyOverlay").hide();
    }

});

var betSlipModel = new BetSlipModel();

new BetSlipView({
    model: betSlipModel,
    el: $("#bet-slip")
});