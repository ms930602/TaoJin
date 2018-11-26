import { mapState } from 'vuex'
export default {
    data() {
        return {

        }
    },
    computed: {
        ...mapState([
            'datadicArr',
            'channelArr',
        ])
    },
    methods: {
        getDatadic() {
            alert(1);
            if (Array.isArray(this.datadicArr)) {
                return new Promise((resolve, reject) => {
                    if (this.datadicArr.length <= 0) {
                        this._ajax({
                            url: this.tootAPI,
                            name: 'dictionary/list',
                            param: {}
                        }).then((d) => {
                            this.$store.commit({
                                type: 'datadic',
                                data: d.aaData,
                            })
                            resolve();
                        })
                    } else {
                        resolve();
                    }
                })
            } else {
                console.log('this.$store.state.datadicArr' + '必须是一个数组')
            }
        },
        getChannel() {
            if (Array.isArray(this.channelArr)) {
                return new Promise((resolve, reject) => {
                    if (this.channelArr.length <= 0) {
                        this._ajax({
                            url: this.tootAPI,
                            name: 'channel/list',
                            param: {}
                        }).then((d) => {
                            this.$store.commit({
                                type: 'channel',
                                data: d.aaData,
                            })
                            resolve();
                        })
                    } else {
                        resolve();
                    }
                })
            } else {
                console.log('this.$store.state.channelArr' + '必须是一个数组')
            }
        },
        _getChannelList() {
            return this._ajax({ url: this.rootAPI, name: 'channel/list' })
        },
        _getChannelListOnUse(channelCode) {
            let param = {
                deleted: 0,
                enabled: 1
            }
            if (channelCode) {
                Object.assign(param, {
                    code: channelCode
                })
            }
            return this._ajax({ url: this.rootAPI, name: 'channel/list', param: param })
        },
        _getChannelNameByCode(code) {
            return this._getChannelList()
                .then(function(d) {
                    var codeName = ''
                    d.aaData.forEach(function(el) {
                        if (el.code = code) {
                            codeName = el.name
                        }
                    })
                    return codeName
                })
        },
    },
}