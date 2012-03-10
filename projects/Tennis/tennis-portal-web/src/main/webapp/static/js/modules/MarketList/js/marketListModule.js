var MarketListModel = Backbone.Model.extend({
    initialize: function() {

    }
});

var MarketListView = Backbone.View.extend({

    events: {
        "click .runner": "_handleRunnerClick"
    },

    initialize: function() {
//        console.log('init');
    },

    _handleRunnerClick: function(event) {
        if(betSlipModel) {
            var $currentTarget = $(event.currentTarget);
            betSlipModel.set({
                selectionName: $currentTarget.find(".runnerName").text(),
                marketFullName: $currentTarget.parent("ol").attr("data-market-name"),
                marketId: $currentTarget.attr("data-market-id"),
                selectionId: $currentTarget.attr("data-selection-id"),
                price: $currentTarget.find(".price").text(),
                stake: "2.0"
            });
        }
    }
});

var marketListModel = new MarketListModel();

new MarketListView({
    model: marketListModel,
    el: $("#market-list")
});