var clazz = clazz || {};

// refreshing
clazz.AccountWalletModel = Backbone.Model.extend( {
    defaults: {
        refreshing: false
    }
})

clazz.AccountWalletView = Backbone.View.extend({
    initialize: function() {
        _.bindAll(this, "_showRefreshOverlay", "_hideRefreshOverlay");
        this.model.on("change:refresh", this._handleRefresh, this)
    },

    events: {
        "click .header .refresh": "_handleRefresh"
    },

    _handleRefresh: function() {
        if(this.model.get("refreshing") === false) {
            this.model.set({refreshing: true});

            this._showRefreshOverlay();
            var that = this;

            $.ajax({url: "/betfair/wallet",
                type: "GET",
                dataType: "html",
                cache: false
            })
            .done(function(data) {
                $('div.content','#account-wallets').html($(data).find('div.content').html());
                that._hideRefreshOverlay();
            })
            .fail(function() {
                console.log('failed');
                // todo - display error
                // that._showNoBetsNotification();
            }).always(function(){
                that.model.set({refresh: false, refreshing: false});
            });
        }

    },

    _showRefreshOverlay: function() {
        this.$el.find(".overlay").show();
    },

    _hideRefreshOverlay: function() {
        this.$el.find(".overlay").hide();
    }
});

var modules = modules || {};
modules.wallet = {};
modules.wallet.model = new clazz.AccountWalletModel();

new clazz.AccountWalletView({
    model: modules.wallet.model,
    el: $("#account-wallets")
});
