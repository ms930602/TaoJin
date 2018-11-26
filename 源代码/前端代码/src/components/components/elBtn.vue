<template>
	<el-button class="btnstyle" :type="type" @click="click" :size="size" :plain="plain">{{text}}</el-button>
</template>
<script>
	export default {
		name: 'elBtn',
		props: {
			text: {
				default: ''
			},
			size: {
				default: 'mini',
			},
			type: {
				default: '',
			},
			plain:{
				default:false
			}
		},
		methods: {
			click() {
				this.$emit('click')
			},
		}
	}
</script>
<style>
    .btnstyle{
        padding: 4px 10px !important;
	}
</style>
