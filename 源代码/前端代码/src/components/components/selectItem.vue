<template>
	<el-select 
		:filterable='filterable'
		:size='selectSize'
		:clearable='selectClearable'
		:placeholder="placeHolder"
		@input='selectChange'
		@blur='selectBlur'
		@change='selectOnChange'
		@focus='selectFocus'
		:value='selectValue'
		>
		<slot></slot>
	</el-select>
</template>
<script>
	export default {
		name: 'selectItem',
		props: {
			selectValue: {
				default: ''
			},
			filterable: {
				default: true
			},
			selectSize: {
				default: 'mini'
			},
			selectClearable: {
				default: true,
			},
			placeHolder: {
				default: '请选择'
			},
		},
		methods: {
			selectBlur() {
				this.$emit('selectBlur')
			},
			selectChange(val) {
				this.$emit('update:selectValue', val)
			},
			selectFocus(){
				this.$emit('selectFocus')
			},
			selectOnChange(){
				this.$emit('selectOnChange')
			}
			
		}
	}
</script>