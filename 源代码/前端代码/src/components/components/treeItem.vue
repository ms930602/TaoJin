<template>
	<el-tree
	  :data="dataList"
	  :show-checkbox="showCheckbox"
	  :node-key="nodeKey"
	  :default-checked-keys="defaultChecked"
	  :props="defaultProps"
	  ref="treeItem">
	</el-tree>
</template>
<script>
	export default {
		name: 'treeItem',
		props: {
			dataList: {
				default: function() {
					return []
				}
			},
			showCheckbox: {
				default: true
			},
			nodeKey: {
				default: 'id'
			},
			defaultChecked: {
				default: function() {
					return []
				}
			},
			defaultProps: {
				default: function() {
					return {
	                  children: 'children',
	                  label: 'name'
	                }
				}
			},
		},
	}
</script>