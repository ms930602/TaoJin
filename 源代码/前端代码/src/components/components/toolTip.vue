<template>
	<el-tooltip effect="dark" :content="content2" :placement="placement">
      <slot></slot>
    </el-tooltip>
</template>
<script>
	export default {
		name: 'toolTip',
		data() {
			return {
			}
		},
		mounted() {
		},
		computed: {
			content2() {
				return this.content + ''
			}
		},
		props: {
			placement: {
				default: 'top',
			},
			content: {
				default: ''
			}
		},
	}
</script>