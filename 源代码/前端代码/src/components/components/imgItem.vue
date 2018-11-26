<template>
	<div>
		<img class="resp-img" :src="imgURL + path" v-if="path">
		<span v-else>暂无图片</span>
	</div>	
</template>
<script>
import configs from '../../configs.js'
	export default {
		name: 'imgItem',
		data() {
			return {
				imgURL: configs.imgURL
			}
		},
		props: {
			path: {
				default: ''
			},
		},
	}
</script>