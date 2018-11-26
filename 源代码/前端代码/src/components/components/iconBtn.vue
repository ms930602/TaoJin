<template>
	<el-tooltip effect="dark" :content="content" placement="top-start" :disabled="isDisabled">
      <el-button :icon="iconClass" @click="click" size="mini" :type="type">
      	<slot></slot>
      </el-button>
    </el-tooltip>
</template>
<script>
	export default {
		name: 'iconBtn',
		props: {
			iconClass: {
				default: ''
			},
			content: {
				default: '',
			},
			type: {
				default: ''
			},
			text: {
				default: false
			},
			isDisabled:{
				default:true
			}
		},
		methods: {
			click() {
				this.$emit('click')
			}
		}
	}
</script>