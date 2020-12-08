<template>
    <div>
        <div>
            <div style="display: flex;justify-content: space-between">
                <div>
                    <el-input placeholder="请输入商品名称进行搜索，可以直接回车搜索..." prefix-icon="el-icon-search"
                              clearable
                              @clear="initOrderList"
                              style="width: 350px;margin-right: 10px" v-model="keyword"
                              @keydown.enter.native="initOrderList" :disabled="showSearchView"></el-input>
                    <el-button icon="el-icon-search" type="primary" @click="initOrderList" :disabled="showSearchView">
                        搜索
                    </el-button>
                    <el-button type="primary" @click="showSearchView = !showSearchView">
                        <i :class="showSearchView?'fa fa-angle-double-up':'fa fa-angle-double-down'"
                           aria-hidden="true"></i>
                        高级搜索
                    </el-button>
                </div>
                <div>
                    <el-upload
                            :show-file-list="false"
                            :before-upload="beforeUpload"
                            :on-success="onSuccess"
                            :on-error="onError"
                            :disabled="importDataDisabled"
                            style="display: inline-flex;margin-right: 8px"
                            action="/employee/basic/import">
                        <el-button :disabled="importDataDisabled" type="success" :icon="importDataBtnIcon">
                            {{importDataBtnText}}
                        </el-button>
                    </el-upload>
                    <el-button type="success" @click="exportData" icon="el-icon-download">
                        导出数据
                    </el-button>
                    <el-button type="primary" icon="el-icon-plus" @click="showAddOrderFormView()">
                        添加数据
                    </el-button>
                </div>
            </div>
            <transition name="slide-fade">
                <div v-show="showSearchView"
                     style="border: 1px solid #409eff;border-radius: 5px;box-sizing: border-box;padding: 5px;margin: 10px 0px;">
                    <el-row>
                        <el-col :span="5">
                            政治面貌:
                            <el-select v-model="searchValue.politicId" placeholder="政治面貌" size="mini"
                                       style="width: 130px;">
                                <el-option
                                        v-for="item in politicsstatus"
                                        :key="item.id"
                                        :label="item.name"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                        </el-col>
                        <el-col :span="8">
                            聘用形式:
                            <el-radio-group v-model="searchValue.engageForm">
                                <el-radio label="劳动合同">劳动合同</el-radio>
                                <el-radio label="劳务合同">劳务合同</el-radio>
                            </el-radio-group>
                        </el-col>
                        <el-col :span="10">
                            入职日期:
                            <el-date-picker
                                    v-model="searchValue.beginDateScope"
                                    type="daterange"
                                    size="mini"
                                    unlink-panels
                                    value-format="yyyy-MM-dd"
                                    range-separator="至"
                                    start-placeholder="开始日期"
                                    end-placeholder="结束日期">
                            </el-date-picker>
                        </el-col>
                        <el-col :span="5" :offset="4">
                            <el-button size="mini">取消</el-button>
                            <el-button size="mini" icon="el-icon-search" type="primary"
                                       @click="initOrderList('advanced')">
                                搜索
                            </el-button>
                        </el-col>
                    </el-row>
                </div>
            </transition>
        </div>


        <div>
            <transition name="slide-fade">
                <div v-show=""
                     style="border: 1px solid #409eff;border-radius: 5px;box-sizing: border-box;padding: 5px;margin: 10px 0px;"></div>
            </transition>
        </div>
        <div style="margin-top: 10px;width: 100%">
            <el-table :data="OrderFormList" stripe border v-loading="loading" element-loading-text="正在加载..."
                      element-loading-spinner="el-icon-loading" element-loading-background="rgba(0, 0, 0, 0.8)"
                      style="width: 100%"><!--:header-cell-style="{'text-align':'center'}"-->
                <el-table-column show-overflow-tooltip prop="id" label="id" v-if="false" align="left"></el-table-column>
                <el-table-column show-overflow-tooltip prop="nickName" label="微信昵称" fixed align="left" width="100"></el-table-column>
                <el-table-column show-overflow-tooltip prop="orderType" label="下单方式" fixed align="left" width="70"></el-table-column>
                <el-table-column show-overflow-tooltip prop="WeChatID" label="微信号" align="left" width="80"></el-table-column>
                <el-table-column show-overflow-tooltip prop="shop" label="商品" align="left" width="160"></el-table-column>
                <el-table-column show-overflow-tooltip prop="orderNumber" label="订单编号" align="left" width="150"></el-table-column>
                <el-table-column show-overflow-tooltip prop="supplier" label="供货商" align="left" width="70"></el-table-column>
                <el-table-column show-overflow-tooltip prop="agencyPrice" label="代理价" align="left" width="70"></el-table-column>
                <el-table-column show-overflow-tooltip prop="sellingPrice" label="售价" align="left" width="70"></el-table-column>
                <el-table-column show-overflow-tooltip prop="payment" label="客户实付" align="left" width="70"></el-table-column>
                <el-table-column show-overflow-tooltip prop="differencesPrice" label="需返差价" align="left" width="70"></el-table-column>
                <el-table-column show-overflow-tooltip prop="courierNumber" label="快递单号" align="left" width="150"></el-table-column>
                <el-table-column show-overflow-tooltip prop="receivingName" label="收货姓名" align="left" width="80"></el-table-column>
                <el-table-column show-overflow-tooltip prop="receivingPhone" label="收货电话" align="left" width="100"></el-table-column>
                <el-table-column show-overflow-tooltip prop="receivingAddress" label="收货地址" align="left" width="150"></el-table-column>
                <el-table-column show-overflow-tooltip prop="largess" label="赠品详情" align="left" width="120"></el-table-column>
                <el-table-column show-overflow-tooltip prop="largessCourierNumber" label="赠品快递单号" align="left" width="150"></el-table-column>
                <el-table-column show-overflow-tooltip prop="orderTime" label="下单时间" fixed="right" width="141"></el-table-column>
                <el-table-column show-overflow-tooltip fixed="right" label="操作" width="97">
                    <template slot-scope="scope">
                        <el-button @click="showEditOrderFormView(scope.row)" style="padding: 3px" size="mini">编辑</el-button>
                        <el-button @click="deleteEmp(scope.row)" style="padding: 3px" size="mini" type="danger">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div style="display: flex;justify-content: flex-end">
                <el-pagination background @current-change="currentChange" @size-change="sizeChange"
                               layout="sizes, prev, pager, next, jumper, ->, total, slot"
                               :total="total"></el-pagination>
            </div>
        </div>


        <div>
            <el-dialog
                    :title="title"
                    :visible.sync="dialogVisible"
                    width="66%" center>
                <div>
                    <el-form :model="OrderForm" :rules="rules" ref="OrderForm">
                        <el-row>
                            <el-col :span="8">
                                <el-form-item label="微信昵称:" prop="nickName">
                                    <el-input size="mini" style="width: 190px" prefix-icon="el-icon-edit"
                                              v-model="OrderForm.nickName" placeholder="请输入微信昵称"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="微信号：   " prop="WeChatID">
                                    <el-input size="mini" style="width: 190px" prefix-icon="el-icon-edit"
                                              v-model="OrderForm.WeChatID" placeholder="请输入微信号"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="淘宝ID：  " prop="AlibabaID">
                                    <el-input size="mini" style="width: 190px" prefix-icon="el-icon-edit"
                                              v-model="OrderForm.AlibabaID" placeholder="请输入淘宝ID"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="8">
                                <el-form-item label="商品：" prop="shop">
                                    <el-input size="mini" style="width: 190px" prefix-icon="el-icon-edit"
                                              v-model="OrderForm.shop" placeholder="请输入下单商品"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="下单方式:" prop="orderType">
                                    <el-select v-model="OrderForm.orderType" placeholder="下单方式" size="mini"
                                               style="width: 190px;">
                                        <el-option
                                                v-for="item in orderTypes"
                                                :key="item"
                                                :label="item"
                                                :value="item">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="下单时间:" prop="orderTime">
                                    <el-date-picker
                                            v-model="OrderForm.orderTime"
                                            size="mini"
                                            type="datetime"
                                            value-format="yyyy-MM-dd HH:mm:ss"
                                            style="width: 180px;"
                                            placeholder="下单时间">
                                    </el-date-picker>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="8">
                                <el-form-item label="供货商： " prop="supplier">
                                    <el-input size="mini" style="width: 190px" prefix-icon="el-icon-edit"
                                              v-model="OrderForm.supplier" placeholder="请输入供货商"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="订单编号:" prop="orderNumber">
                                    <el-input size="mini" style="width: 190px" prefix-icon="el-icon-edit"
                                              v-model="OrderForm.orderNumber" placeholder="请输入订单编号"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="快递单号:" prop="courierNumber">
                                    <el-input size="mini" style="width: 190px" prefix-icon="el-icon-edit"
                                              v-model="OrderForm.courierNumber" placeholder="快递单号"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="8">
                                <el-form-item label="代理价： " prop="agencyPrice">
                                    <el-input size="mini" style="width: 190px" prefix-icon="el-icon-edit"
                                              v-model.number="OrderForm.agencyPrice" placeholder="请输入代理价"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="售价：      " prop="sellingPrice">
                                    <el-input size="mini" style="width: 190px" prefix-icon="el-icon-edit"
                                              v-model.number="OrderForm.sellingPrice" placeholder="请输入售价"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="客户实付:" prop="payment">
                                    <el-input size="mini" style="width: 190px" prefix-icon="el-icon-edit"
                                              v-model.number="OrderForm.payment" placeholder="请输入客户实付"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="8">
                                <el-form-item label="需返差价:" prop="differencesPrice">
                                    <el-input size="mini" style="width: 190px" prefix-icon="el-icon-edit"
                                              v-model.number="OrderForm.differencesPrice" placeholder="请输入需返差价"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="赠品详情:" prop="largess">
                                    <el-input size="mini" style="width: 190px" prefix-icon="el-icon-edit"
                                              v-model="OrderForm.largess" placeholder="赠品详情"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="赠品单号:" prop="largessCourierNumber">
                                    <el-input size="mini" style="width: 190px" prefix-icon="el-icon-edit"
                                              v-model="OrderForm.largessCourierNumber" placeholder="赠品快递单号"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="8">
                                <el-form-item label="收货姓名:" prop="receivingName">
                                    <el-input size="mini" style="width: 190px" prefix-icon="el-icon-edit"
                                              v-model="OrderForm.receivingName" placeholder="收货姓名"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="收货电话:" prop="receivingPhone">
                                    <el-input size="mini" style="width: 190px" prefix-icon="el-icon-phone"
                                              v-model="OrderForm.receivingPhone" placeholder="收货电话"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="收货地址:" prop="receivingAddress">
                                    <el-input size="mini" style="width: 190px" prefix-icon="el-icon-edit"
                                              v-model="OrderForm.receivingAddress" placeholder="收货地址"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </div>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="dialogVisible = false">取 消</el-button>
                    <el-button type="primary" @click="doAddOrderForm()">确 定</el-button>
                </span>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    import { MessageBox } from "element-ui";
    export default {
        name: "OrderList",
        mounted() {
            // this.initData();
            this.initOrderList();
        },
        data() {
            return {
                user: JSON.parse(window.sessionStorage.getItem("user")),
                searchValue: {
                    politicId: null,
                    engageForm: null,
                    beginDateScope: null
                },
                showSearchView: false,
                OrderFormList: [],
                loading: false,
                total: 0,
                page: 1,
                size: 20,
                importDataBtnText: '导入数据',
                importDataBtnIcon: 'el-icon-upload2',
                importDataDisabled: false,
                keyword: '',
                politicsstatus: [],
                title: '',
                dialogVisible: false,
                OrderForm: {
                    id: null,
                    agentCode: null,
                    nickName: null,
                    WeChatID: null,
                    AlibabaID: null,
                    shop: null,
                    orderType: null,
                    orderNumber: null,
                    supplier: null,
                    agencyPrice: null,
                    sellingPrice: null,
                    payment: null,
                    differencesPrice: null,
                    courierNumber: null,
                    receivingName: null,
                    receivingPhone: null,
                    receivingAddress: null,
                    largess: null,
                    largessCourierNumber: null,
                    orderTime: null,
                },
                orderTypes: ['微信', '闲鱼', '旗舰店', '其他'],
                rules: {
                    receivingPhone: [{message: '请输入电话号码', trigger: 'blur'},
                        {
                            pattern: /^1[3-9]\d{9}$/,
                            message: '手机号格式错误'
                        }],
                    agencyPrice: [{type: 'number', message: '请输入数值', trigger: 'blur'}],
                    sellingPrice: [{type: 'number', message: '请输入数值', trigger: 'blur'}],
                    payment: [{type: 'number', message: '请输入数值', trigger: 'blur'}],
                    differencesPrice: [{type: 'number', message: '请输入数值', trigger: 'blur'}],
                }
            }
        },
        methods: {
            initOrderList(type) {
                this.loading = true;
                let url = '/order/basic/?page=' + this.page + '&size=' + this.size;
                if (type && type == 'advanced') {
                    if (this.searchValue.politicId) {
                        url += '&politicId=' + this.searchValue.politicId;
                        if (this.searchValue.engageForm) {
                            url += '&engageForm=' + this.searchValue.engageForm;
                        }
                        if (this.searchValue.beginDateScope) {
                            url += '&beginDateScope=' + this.searchValue.beginDateScope;
                        }
                    } else {
                        url += "&name=" + this.keyword;
                    }
                }
                this.getRequest(url).then(resp => {
                    this.loading = false;
                    if (resp) {
                        this.OrderFormList = resp.data;
                        this.total = resp.total;
                    }
                });
            },
            exportData() {
                window.open('/employee/basic/export', '_parent');
            },
            initData() {
            },
            onError(err, file, fileList) {
                this.importDataBtnText = '导入数据';
                this.importDataBtnIcon = 'el-icon-upload2';
                this.importDataDisabled = false;
            },
            onSuccess(response, file, fileList) {
                this.importDataBtnText = '导入数据';
                this.importDataBtnIcon = 'el-icon-upload2';
                this.importDataDisabled = false;
                this.initEmps();
            },
            beforeUpload() {
                this.importDataBtnText = '正在导入';
                this.importDataBtnIcon = 'el-icon-loading';
                this.importDataDisabled = true;
            },
            currentChange(currentPage) {
                this.page = currentPage;
                this.initOrderList('advanced');
            },
            sizeChange(currentSize) {
                this.size = currentSize;
                this.initOrderList();
            },
            showEditOrderFormView(data) {
                this.title = '编辑订单信息';
                let url = '/order/basic/orderFormInfo?id=' + data.id;
                this.postRequest(url, this.OrderForm).then(resp => {
                    this.loading = false;
                    if (resp) {
                        this.OrderForm = resp;
                    }
                });
                this.dialogVisible = true;
            },
            showAddOrderFormView(){
                this.emptyOrderForm();
                this.title = '新建订单';
                this.dialogVisible = true;
            },
            emptyOrderForm() {
                this.OrderForm = {
                    id: null,
                    agentCode: null,
                    nickName: null,
                    WeChatID: null,
                    AlibabaID: null,
                    shop: null,
                    orderType: null,
                    orderNumber: null,
                    supplier: null,
                    agencyPrice: null,
                    sellingPrice: null,
                    payment: null,
                    differencesPrice: null,
                    courierNumber: null,
                    receivingName: null,
                    receivingPhone: null,
                    receivingAddress: null,
                    largess: null,
                    largessCourierNumber: null,
                    orderTime: null,
                }
            },
            deleteEmp() {
            },
            doAddOrderForm() {
                this.$refs['OrderForm'].validate((valid) => {  //开启校验
                    if (valid){
                        this.OrderForm.agentCode = this.user.id;
                        let url = '/order/basic/addOrderForm';
                        this.postRequest(url, this.OrderForm).then(resp => {
                            if (resp) {
                                this.dialogVisible = false;
                                this.initOrderList();
                            }
                        });
                    }else {
                        MessageBox.alert("数据校验未通过,请检查", "提示");
                        return false;
                    }
                });
            },
        }
    }
</script>

<style>
    /*.slide-fade enter-active {
        transition: all .8s ease;
    }

    .slide-fade leave-active {
        transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);
    }

    .slide-fade enter, .slide-fade leave-to
        !* .slide-fade-leave-active for below version 2.1.8 *!
    {
        transform: translateX(10px);
        opacity: 0;
    }*/
    .el-form-item label:after {
        content: "";
        display: inline-block;
        width: 100%;
    }

    .el-form-item label {
        text-align: justify;
        height: 40px;
    }

    .el-form-item.is-required .el-form-item label:before {
        content: none !important;
    }

</style>