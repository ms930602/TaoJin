<template>
	<el-select :value="value" :disabled='disabled' :placeholder="placeHolder" :size="inputSize" @input="inputChange" @change="selectChange" :filterable="filterable" :clearable="clearable">
	    <slot></slot>
  	</el-select>
</template>
<script>
	export default {
		name: 'selectInput',
		props: {
			placeHolder: {
				default: '请选择'
			},
			value: {
				default: '',
			},
			inputSize: {
				default: 'mini',
			},
			options: {
				default: function() {
					return []
				}
			},
			filterable: {
				default: false
			},
			clearable: {
				default: false
			},
			disabled:{
				default:false
			}
		},
		methods: {
			inputChange(value) {
				this.$emit('update:value', value)
			},
			selectChange(val) {
				this.$emit('selectChange', val)
			}
		}
	}
</script>