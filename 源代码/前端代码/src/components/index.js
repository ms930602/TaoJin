import cascaderItem from './components/cascaderItem.vue';
import dateEditorDaterange from './components/dateEditorDaterange.vue';
import elBtn from './components/elBtn.vue';
import elemTable from './components/elemTable.vue';
import iconBtn from './components/iconBtn.vue';
import imgItem from './components/imgItem.vue';
import inputItem from './components/inputItem.vue';
import optionItems from './components/optionItems.vue';
import rateItem from './components/rateItem.vue';
import searchInputItem from './components/searchInputItem.vue';
import searchInputItems from './components/searchInputItems.vue';
import selectInput from './components/selectInput.vue';
import selectItem from './components/selectItem.vue';
import switchItem from './components/switchItem.vue';
import tagItem from './components/tagItem.vue';
import toolTip from './components/toolTip.vue';
import treeItem from './components/treeItem.vue';
import uploadItem from './components/uploadItem.vue';

const components = [
	cascaderItem,
	dateEditorDaterange,
	elBtn,
	elemTable,
	iconBtn,
	imgItem,
	inputItem,
	optionItems,
	rateItem,
	searchInputItem,
	searchInputItems,
	selectInput,
	selectItem,
	switchItem,
	tagItem,
	toolTip,
	treeItem,
	uploadItem,
]

const install = function(Vue, opts = {}) {
  components.map(component => {
    Vue.component(component.name, component);
  });
};

export default {
	install
}