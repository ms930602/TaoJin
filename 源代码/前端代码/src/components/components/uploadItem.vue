<template>
	<el-upload
      :action="uploadURL"
      :file-list="fileList"
      :on-success="uploadSuccess"
      :data="uploadData"
      :limit="limit"
      :multiple='multiple'
      :on-remove="onRemove"
      :list-type="listType"
      :before-upload="beforeUpload">
      <elBtn text="点击上传" type="primary" v-if="buttonType === 'button'"></elBtn>
      <i class="el-icon-plus" v-if="buttonType === 'icon'"></i>
      <!-- <div slot="tip" class="el-upload__tip">{{uploadTip}}</div> -->
    </el-upload>
</template>
<script>
import configs from '../../configs.js'
import local from '../../local.js'
import elBtn from './elBtn.vue'
	export default {
		name: 'uploadItem',
		components: {elBtn},
		data() {
			return {
				uploadURL: ''
			}
		},
		props: {
			fileList: {
				default: function() {
					return []
				}
			},
			uploadData: {
				default: function() {
					return {}
				}
			},
			limit: {
				default: 1
			},
			uploadTip:{
				default:'只能上传jpg/png文件，且不超过500kb'
			},
			listType:{
				default:'picture'
			},
			multiple:{
				type:Boolean,
				default:false,
			},
			buttonType: {
				default: "button"
			}
		},
		methods: {
			uploadSuccess(response, file, fileList) {
				this.$emit('success', response, file, fileList)
			},
			onRemove(file, fileList) {
				this.$emit('remove', file, fileList)
			},
			beforeUpload(file) {
				return new Promise((resolve, reject) => {
		          this.$nextTick(() => {

		            var fileType = file.type
		            //图片限制4M，其他附件限制50M
					let limitType = 4

					if(fileType.indexOf('android')===-1){
						this.$message.error('只能上传apk格式文件');
						reject();
					}
					if(typeof fileType === 'string' && fileType.indexOf("image") === -1) {
						this.uploadURL = configs.uploadFileURL + '/upload?token=' + local.get('token')+'&file_FILENAME='+file.name
						limitType = 50
					}else if(typeof fileType === 'string') {
						this.uploadURL = configs.uploadFileURL + '/imgUpload?token=' + local.get('token')
					}
			        const isLt = file.size / 1024 / 1024 < limitType;
			        if (!isLt) {
			          this.$message.error('上传文件大小不能超过 '+limitType+'MB!');
			          reject()
			        }else {
			        	resolve()
			        }
		          })
		        })			
	      	}
		}
	}
</script>