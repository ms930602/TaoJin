<template>
	<span v-if="disabled">{{value}}</span>
	<el-input :placeholder="placeHolder" :class='i_search' :readonly="readonly" :autosize="autosize" :size="inputSize" :value="value" @focus='focus' @change='inputChange' @blur="blur" @input="change" :type="type" @keyup.native.enter="enter" @keyup.native="keyUp" :maxlength="maxlength" v-else>
		<div slot="append" v-if="append">
			<slot name="append"></slot>
		</div>
		<div slot="suffix" v-if="suffix">
			<slot name="suffix"></slot>
		</div>
	</el-input>
</template>
<script>
	export default {
		name: 'inputItem',
		props: {
			disabled:{
				default:false
			},
			placeHolder: {
				default: '请输入内容'
			},
			value: {
				default: '',
			},
			inputSize: {
				default: 'mini',
			},
			type: {
				default: 'text',
			},
			append: {
				default: false
			},
			suffix: {
				default: false
			},
			i_search:{
				default: '',
			},
			autosize: {
				default: false
			},
			readonly: {
				default: false
			},
			maxlength: {
				default: 1000
			}
		},
		methods: {
			blur(e) {
				this.$emit('blur', e.target.value)
			},
			change(value) {
				this.$emit('update:value', $.trim(value))
			},
			focus(){
				this.$emit('focus')
			},
			inputChange(){
				this.$emit('change')
			},
			enter() {
				this.$emit('enter')
			},
			keyUp() {
				this.$emit('keyUp')
			}
		}
	}
</script>