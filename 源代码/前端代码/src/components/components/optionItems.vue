<template>
	<div class="option-items clearfix">
		<div class="left-items">
			<slot name="left"></slot>
		</div>
		<div class="right-items">
			<slot name="right"></slot>
		</div>
	</div>
</template>
<script>
	export default {
		name: 'optionItems',
	}
</script>