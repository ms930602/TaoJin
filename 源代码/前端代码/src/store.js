export default {
	state: {
		loading: false,
		winWidth: window.innerWidth,
		datadicArr:[], //通知类型
		channelArr:[],
		today: moment().subtract(0, 'days').format('YYYY-MM-DD'),
		beforeDay_7: moment().subtract(7, 'days').format('YYYY-MM-DD'),
		beforeDay_30: moment().subtract(30, 'days').format('YYYY-MM-DD'),
	},
	mutations: {
    	loading(state, obj) {
	        state.loading = obj.state
	    },
	    datadic(state, obj) {
	    	state.datadicArr = obj.data
	    },
	    channel(state, obj){
	    	state.channelArr = obj.data
	    }
    },
    getters: {
    	getLoading(state) {
			return state.loading;
		},
		getWinWidth(state) {
			return state.winWidth;
		},
		getDatadicArrNotice(state) {
			return state.datadicArr.filter(o=>o.dataType=='MESSAGE_TYPE');
		},
		getDatadicArrChildren:state=>id=>{
			return state.datadicArr.find(o => o.id == id)
		},
		getChannelArrChildren:state=>code=>{
			return state.channelArr.find(o => o.code == code)
		},
    },
    actions: {
    	loading: ({
			commit
		}, obj) => {
			return new Promise((resolve, reject) => {
				commit('loading', obj);
				resolve()
			});
		},
		datadic({dispatch,commit,state},obj){
			return new Promise((resolve, reject) => {
//		      	setTimeout(() => {
			        	commit({
			        		type:'datadic',
			        		data:obj.data
			        	})
			        resolve()
//			    }, 3000)
			})
		},
		channel({dispatch,commit,state},obj){
			return new Promise((resolve, reject) => {
			        	commit({
			        		type:'channel',
			        		data:obj.data
			        	})
			        resolve()
			})
		},
    }
}