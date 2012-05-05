//var MarketListModel = Backbone.Model.extend({
//    initialize: function() {
//
//    }
//});

var ManualDataReplicationView = Backbone.View.extend({

    events: {
        "click li a": "_handleReplicationActionClick"
    },

    initialize: function() {
//        console.log('init');
    },

    _handleReplicationActionClick: function(event) {
        console.log('clicked');
        $.post('/betfair/today-market-replication', function(data) {
            console.log('ok', data.status);
        });

        event.preventDefault();
        return false;
    }
});

new ManualDataReplicationView({
    el: $(".replications")
});