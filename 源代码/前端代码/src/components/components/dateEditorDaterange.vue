<template>
	
		<el-date-picker 
			:size='dateSize'
			:value='dateValue' 
			:type="type" 
			align="right" 
			:style="datestyle"
			:unlinkPanels='unlinkPanels'
			:editable='editable' 
			:value-format="valueFormat"
			@input="dateChange"
			@change='onChangeDate'
			:range-separator="rangeSeparator" 
			:start-placeholder="startPlaceholder" 
			:end-placeholder="endPlaceholder" 
			:placeholder='placeholder'
			:picker-options="pickerOptions"
			:disabledBeforeDate='disabledBeforeDate'
			:disabledAfterDate='disabledAfterDate'
			:clearable='dateClearable' v-if="showPickerOptions">
	    </el-date-picker>
	    <el-date-picker 
			:size='dateSize'
			:value='dateValue' 
			:type="type" 
			align="right" 
			:style="datestyle"
			:unlinkPanels='unlinkPanels'
			:editable='editable' 
			:value-format="valueFormat"
			@input="dateChange"
			@change='onChangeDate'
			:range-separator="rangeSeparator" 
			:start-placeholder="startPlaceholder" 
			:end-placeholder="endPlaceholder" 
			:placeholder='placeholder'
			:disabledBeforeDate='disabledBeforeDate'
			:disabledAfterDate='disabledAfterDate'
			:clearable='dateClearable' v-else>
	    </el-date-picker>
	
</template>

<script>
export default{
	name: 'dateEditorDaterange',
	props:{
		datestyle:{
			default:''
		},
		dateSize:{
			default:'mini'
		},
		type:{
			default:'daterange'
		},
		dateValue:{
			type: [Array,String],
//			default: function() {
//				return []
//			}
		},
		unlinkPanels:{
			type:Boolean,
			default:true
		},
		editable: {
			default: false,
			type:Boolean
		},
		dateClearable: {
			default: true,
			type:Boolean
		},
		valueFormat:{
			default:'yyyy-MM-dd'
		},
		rangeSeparator:{
			default:'至'
		},
		startPlaceholder:{
			default:'开始日期'
		},
		endPlaceholder:{
			default:'结束日期'
		},
		disabledBeforeDate:{
			default:'',
		},
		disabledAfterDate:{
			default:'',
		},
		placeholder:{
			default:'选择日期'
		},
		showPickerOptions: {
			default: true
		},
		showShortcuts: {
			default: true
		}
	},
	data(){
		return {
			pickerOptions:{}
		}
	},
	mounted(){
		var pickerOption={};
		var vm=this
		
		pickerOption.disabledDate=function(time){
			if(!vm.disabledBeforeDate&&!vm.disabledAfterDate){
				return false //表示全部可以选
 			}else {
 				if(!vm.disabledBeforeDate){
   					return time.getTime() > new Date(vm.disabledAfterDate+" 23:59:59").getTime()
 				}else if(!vm.disabledAfterDate){
   					return time.getTime() < new Date(vm.disabledBeforeDate+" 23:59:59").getTime()
 				}else{
   					if(time.getTime() < new Date(vm.disabledBeforeDate+" 23:59:59").getTime()){
						return time.getTime() < new Date(vm.disabledBeforeDate+" 23:59:59").getTime();
					}else{
						return time.getTime() > new Date(vm.disabledAfterDate+" 23:59:59").getTime();
					}
 				}
 			}
		}
		if(this.showShortcuts) {
			pickerOption.shortcuts=[{
		        text: '最近一周',
		        onClick(picker) {
			        const end = new Date();
			        const start = new Date();
			        start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
			        picker.$emit('pick', [start, end]);
		        }
		        }, {
		            text: '最近一个月',
		            onClick(picker) {
				        const end = new Date();
		 	            const start = new Date();
			            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
			            picker.$emit('pick', [start, end]);
		            }
		        }, {
		            text: '最近三个月',
		            onClick(picker) {
			            const end = new Date();
			            const start = new Date();
			            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
			            picker.$emit('pick', [start, end]);
		        	}
		        }]
		    }		
		this.pickerOptions= pickerOption
	},
	methods:{
		dateChange(val) {
			this.$emit('update:dateValue', val)
		},
		onChangeDate(val){
			this.$emit('change',val)
		}
	},
}
</script>

<style>
</style>