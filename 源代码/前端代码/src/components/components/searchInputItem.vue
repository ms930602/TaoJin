<template>
	<div :class='codeNameF' class="flex-y-center option-box">
		<span class="name">{{name}}:</span>
		<div class="flex-x-between flex-y-center">
			<slot></slot>
		</div>		
	</div>
</template>
<script>
	export default {
		name: 'searchInputItem',
		props: {
			name: {
				default: ''
			}
		}
	}
</script>