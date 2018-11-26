
<template>
<!-- 1X -->
    <div>
<el-row :gutter="20">
<el-col :span="6">

  <el-card class="box-card">
      <img src="lib/img/icon_ru@2x.png" style="float:left">
      <div class="ct-title">
        <h5>今日入库单</h5>

        <div class="ct-title-text">
          <span>已完成(笔):{{ctA.ok}}</span>
          <span>未完成(笔):{{ctA.nil}}</span> 
        </div> 
      </div>
  </el-card>

</el-col>
<el-col :span="6">
  
  <el-card class="box-card">
      <img src="lib/img/icon_chu@2x.png" style="float:left">
      <div class="ct-title">
        <h5>今日出库单</h5>

        <div class="ct-title-text">
          <span>已完成(笔):{{ctB.ok}}</span>
          <span>未完成(笔):{{ctB.nil}}</span> 
        </div> 
      </div>
  </el-card>

</el-col>
<el-col :span="6">
  
  <el-card class="box-card">
      <img src="lib/img/icon_pan@2x.png" style="float:left">
      <div class="ct-title">
        <h5>今日盘点单</h5>

        <div class="ct-title-text">
          <span>已完成(笔):{{ctC.ok}}</span>
          <span>未完成(笔):{{ctC.nil}}</span>
        </div> 
      </div>
  </el-card>

</el-col>
<el-col :span="6">
  
  <el-card class="box-card">
      <img src="lib/img/icon_tiao@2x.png" style="float:left">
      <div class="ct-title">
        <h5>今日调整单</h5>

        <div class="ct-title-text">
          <span>已完成(笔):{{ctD.ok}}</span>
          <span>未完成(笔):{{ctD.nil}}</span> 
        </div> 
      </div>
  </el-card>

</el-col>

</el-row>

<br>

<!-- 2X -->
<el-row :gutter="20">
  <el-col :span="8">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <h5>库存统计</h5>
        <p class="ct-title-text">统计【可用总库存数量】及当天【入库数量】、【出库数量】</p>
      </div>
      <div id="CAdiv" style="height:380px"></div>
    </el-card>
  </el-col>
  <el-col :span="8">
  <div class="lltA">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <div style="float:left">
          <h5>卷烟入库排行榜</h5>
          <p class="ct-title-text">统计时间段内卷烟规格入库量排名（前十名）</p>
        </div>
        <selectInput :value.sync="statusA" @selectChange="searchTableA" style="float:right;width:100px" >
          <el-option
              v-for="item in dayOption"
              :key="item.key"
              :label="item.value"
              :value="item.key">
            </el-option>
        </selectInput>
      </div>
      <div style="height:380px;">
          <div v-for="item in rankA">
            <div style="margin-top:3px">
              <span style="color:#FFFFFF">{{item.materName}}</span>
              <span style="float:right;color:#FFFFFF">{{item.sum}}件</span>
              <el-progress :percentage="item.ex"  color="#FFFFFF" :stroke-width="10" :show-text="false" >
              </el-progress>
            </div>
          </div>
      </div>
    </el-card>
  </div>
  </el-col>
  <el-col :span="8">
  <div class="lltB">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <div style="float:left">
          <h5>卷烟出库排行榜</h5>
          <p class="ct-title-text">统计时间段内卷烟规格出库量排名（前十名）</p>
        </div>
        <selectInput :value.sync="statusB" @selectChange="searchTableB" style="float:right;width:100px" >
          <el-option
              v-for="item in dayOption"
              :key="item.key"
              :label="item.value"
              :value="item.key">
            </el-option>
        </selectInput>
      </div>
      <div style="height:380px;">
          <div v-for="item in rankB">
            <div style="margin-top:3px">
              <span style="color:#FFFFFF">{{item.materName}}</span>
              <span style="float:right;color:#FFFFFF">{{item.sum}}件</span>
              <el-progress :percentage="item.ex" color="#FFFFFF" :stroke-width="10" :show-text="false" >
              </el-progress>
            </div>
          </div>
      </div>
    </el-card>
    </div>
  </el-col>
</el-row>
<br/>
<el-row :gutter="20">
  <el-col :span="16">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <div style="float:left">
          <span>库存预警</span>
          <!-- <p class="ct-title-text">统计7天内呆滞 超储 短缺</p> -->
        </div>
      </div>
      <div id="CWarnDiv" style="height:300px;"></div>
    </el-card>
  </el-col>
  <el-col :span="8">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <div >
          <span>货位情况占比统计</span>
        </div>
      </div>

      <div id="CBiShuDiv" style="height:300px"></div>
    </el-card>
  </el-col>
</el-row>
    </div>

</template>

<script>
import mixin from "../mixin/mixin.js";
import local from "../local.js";
var sorterBar = null,
  productBar = null;
export default {
  mixins: [mixin],
  data() {
    return {
      ctA:{//今日入库单
        count:0,
        ok:0,
        nil:0
      },
      ctB:{//今日出库单
        count:0,
        ok:0,
        nil:0
      },
      ctC:{//今日盘点单
        count:0,
        ok:0,
        nil:0
      },
      ctD:{//今日调整单
        count:0,
        ok:0,
        nil:0
      },
      statusA:7,
      statusB:7,
      statusC:7,
      dayOption:[
        {key:7 , value:'近7天'},
        {key:30 , value:'近30天'}
      ],
      CAChars:null,
      CBChars:null,
      CWarnChars:null,
      rankA:[],
      rankB:[],
      rankC:[]
    };
  },
  mounted() {
    // this.initCA();
    // this.initBishu();
    // this.initWarning();
    // this.searchTableA(7);
    // this.searchTableB(7);
    // this.loadData();
    //this.searchWarning(7)
  },
  methods: {
    searchWarning(day){
      this._ajax({url: this.rootAPI + 'home/StockwarningRank?day='+day})
        .then((function(d) {
            if(d.state === 0) {

            }else
            {
              this.$message({ type: "error", message: "服务器错误!" });
            }
        }).bind(this))
    },
    loadData(){
     this._ajax({url: this.rootAPI + 'home/queryHome'})
        .then((function(d) {
            if(d.state === 0) {

              this.ctA.count = d.aaData.ctEntrymainorder.countData || 0;
              this.ctA.ok = d.aaData.ctEntrymainorder.sumOk || 0;
              this.ctA.nil = this.ctA.count - this.ctA.ok;
              this.ctB.count = d.aaData.ctOutmainorder.countData || 0;
              this.ctB.ok = d.aaData.ctOutmainorder.sumOk || 0;
              this.ctB.nil = this.ctB.count - this.ctB.ok;
              this.ctC.count = d.aaData.ctStockcheck.countData || 0;
              this.ctC.ok = d.aaData.ctStockcheck.sumOk || 0;
              this.ctC.nil = this.ctC.count - this.ctC.ok;
              this.ctD.count = d.aaData.ctPositionadjust.countData || 0;
              this.ctD.ok = d.aaData.ctPositionadjust.sumOk || 0;
              this.ctD.nil = this.ctD.count - this.ctD.ok;
              var CAData = [];
              CAData.push(d.aaData.countInventoryDay || 0);
              CAData.push(d.aaData.countEntryDay || 0);
              CAData.push(d.aaData.countOutmainorderDay || 0);
              this.CAChars.setOption({
                series : [
                    {
                      data:CAData,
                      // itemStyle : { 
                      //   normal: {
                      //     label : {
                      //       show: true,
                      //       position: 'top'
                      //       }
                      //       }
                      //       },
                    }
                ]
              });
              var cb = d.aaData.ctInventoryBi;
              var ka = 0,kb = 0;

              if(cb.length == 2){
                ka = cb[0].statusSum
                kb = cb[1].statusSum
              }else if(cb.length == 1){
                if(cb[0].status == 0){
                  ka = cb[0].statusSum
                }else{
                  kb = cb[0].statusSum
                }
              }
              this.CBChars.setOption({
                series: [
                    {
                        data:[
                            {value:kb, name:'占用',itemStyle:{
                              color:"#1E88E5"
                            }},
                            {value:ka, name:'闲置',itemStyle:{
                              color:"#26C6DA"
                            }}
                        ]
                    }
                ]
              });

            }else
            {
              this.$message({ type: "error", message: "服务器错误!" });
            }
        }).bind(this))
    },
    initWarning(){
      this.CWarnChars = echarts.init(document.getElementById("CWarnDiv"));
      let warnoption = {
              tooltip : {
                  trigger: 'axis',
                  axisPointer: {
                      type: 'shadow',
                      label: {
                          show: true
                      }
                  }
              },
              toolbox: {
                  show : true,
                  feature : {
                      mark : {show: true},
                      dataView : {show: true, readOnly: false},
                      magicType: {show: true, type: ['line', 'bar']},
                      restore : {show: true},
                      saveAsImage : {show: true}
                  }
              },
              calculable : true,
              legend: {
                  data:['Growth', '超储(件)', '呆滞(天)','短缺(件)'],
                  itemGap: 5
              },
              grid: {
                  top: '12%',
                  left: '1%',
                  right: '10%',
                  containLabel: true
              },
              xAxis: [
                  {
                      type : 'category',
                      data : []
                  }
              ],
              yAxis: [
                  {
                      type : 'value',
                      name : ''
                      
                  }
              ],
              dataZoom: [
                  {
                      show: true,
                      start: 0,
                      end: 100
                  },
                  {
                      type: 'inside',
                      start: 0,
                      end: 100
                  },
                  {
                      show: true,
                      yAxisIndex: 0,
                      filterMode: 'empty',
                      width: 30,
                      height: '80%',
                      showDataShadow: false,
                      left: '93%'
                  }
              ],
              series : [
                  {
                      name: '超储(件)',
                      type: 'bar',
                      data: []
                  },
                  {
                      name: '呆滞(天)',
                      type: 'bar',
                      data: []
                  },
                  {
                      name: '短缺(件)',
                      type: 'bar',
                      data: []
                  }
              ]
          };
          //this.CWarnChars.setOption(warnoption);
      this._ajax({url: this.rootAPI + 'inventorydetail/getinventdetailwarn'})
        .then((function(d) {
            if(d.state === 0) {
                  warnoption.xAxis[0].data= d.aaData[0];
                  warnoption.series[0].data= d.aaData[1];
                  warnoption.series[1].data= d.aaData[2];
                  warnoption.series[2].data= d.aaData[3];
                  this.CWarnChars.setOption(warnoption);
            }else
            {
              this.$message({ type: "error", message: "服务器错误!" });
              this.CWarnChars.setOption(warnoption);
            }
        }).bind(this))
      
      
      
      
      
      
    },
    initBishu(){
      this.CBChars = echarts.init(document.getElementById("CBiShuDiv"));
      var option = {
          tooltip : {
              trigger: 'item',
              formatter: "{a} <br/>{b} : {c} ({d}%)"
          },
          
          series : [
              {
                  name: '货位',
                  type: 'pie',
                  radius : '55%',
                  center: ['50%', '50%'],
                  label: {
                    normal: {
                        formatter: ' {b|{b}：}  {per|{d}%}  ',
                        backgroundColor: '#eee',
                        borderColor: '#aaa',
                        borderWidth: 1,
                        borderRadius: 4,
                        rich: {
                            a: {
                                color: '#999',
                                lineHeight: 22,
                                align: 'center'
                            },
                            hr: {
                                borderColor: '#aaa',
                                width: '100%',
                                borderWidth: 0.5,
                                height: 0
                            },
                            b: {
                                fontSize: 16,
                                lineHeight: 33
                            },
                            per: {
                                color: '#eee',
                                backgroundColor: '#334455',
                                padding: [2, 4],
                                borderRadius: 2
                            }
                        }
                    }
            },
                  data: [],
                  itemStyle: {
                      emphasis: {
                          shadowBlur: 10,
                          shadowOffsetX: 0,
                          shadowColor: 'rgba(0, 0, 0, 0.5)'
                      }
                  }
              }
          ]
      };
      this.CBChars.setOption(option);
    },
    initCA(){
      this.CAChars = echarts.init(document.getElementById("CAdiv"));
      var seriesLabel = {
    normal: {
        show: true,
        textBorderColor: '#333',
        position:"top",
        textBorderWidth: 0
    }
}
      var option = {
            color: ['#3398DB'],
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'category',
                    data : ['总库存量', '入库数量', '出库数量'],
                    label:seriesLabel,
                    axisTick: {
                        alignWithLabel: true
                    }
                }
            ],
            yAxis : [
                {
                    type : 'value',                   
                    name:'单位:件'
                }
            ],
            series : [
                {
                    // name:'直接访问',
                    type:'bar',
                    label: seriesLabel,
                    barWidth: '20%',
                    data:[]
                }
            ]
        };
        this.CAChars.setOption(option);
    },
    searchTableA(day){
      this._ajax({url: this.rootAPI + 'home/entryorderRank?day='+day})
        .then((function(d) {
            if(d.state === 0) {
              var rankACount = 0;
              var datas = d.aaData;
              datas.forEach(temp=>{
                rankACount += (temp.sum * 1);
              })
              datas.forEach(temp=>{
                temp.ex = (temp.sum / rankACount * 100);
              })
              this.rankA = datas;
            }else
            {
              this.$message({ type: "error", message: "服务器错误!" });
            }
        }).bind(this))
    },
    searchTableB(day){
      this._ajax({url: this.rootAPI + 'home/OutorderRank?day='+day})
        .then((function(d) {
            if(d.state === 0) {
              var rankACount = 0;
              var datas = d.aaData;
              console.log(datas);
              datas.forEach(temp=>{
                rankACount += (temp.sum * 1);
              })
              datas.forEach(temp=>{
                temp.ex = (temp.sum / rankACount * 100);
              })
              this.rankB = datas;
            }else
            {
              this.$message({ type: "error", message: "服务器错误!" });
            }
        }).bind(this))
    },
    searchTableC(day){
      console.log(day)
    }
  }
};
</script>
<style type="text/css">
  .lltA .el-card__body{
    background-color: rgb(30, 187, 207);
  }
  .lltB .el-card__body{
    background-color:#3B71C8;
  }
  .ct-title{
    margin-left:90px;
    margin-top:10px;
  }
  .ct-title-text{
    margin-top:10px;
    color:#8492a6;
  }
  .el-progress-bar__outer{
    background-color: transparent;
  }
  .el-progress-bar__inner{
    opacity: 0.5;
  }
</style>