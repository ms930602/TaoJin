<template>
	<el-tag :type="type">{{text}}</el-tag>
</template>
<script>
	export default {
		name: 'tagItem',
		props: {
			text: {
				default: ''
			},
			type: {
				default: '',
			}
		},
	}
</script>