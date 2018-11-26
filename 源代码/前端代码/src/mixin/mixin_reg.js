import configs from '../configs.js'
export default {
    data() {
        return {
            RegExp: /^-?\d*\.?\d+$/,
            TelePhone: /^1[34578]\d{9}$/,
            LicensePlate: /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4,5}[A-Z0-9挂学警港澳]{1}$/,
            CarZuowei: /^(\\\d+)|((\\s&&[^\\f\\n\\r\\t\\v])*)$/,
            DriverPhone: /^(1[0-9]{10}$)|(0\\\d{2}\\\d{8}(-\\\d{1,4})?$)|(0\\\d{3}\\\d{7,8}(-\\\d{1,4})?$)$/,
            ThePassword: /^.*(?=.{8,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$/,
            RiskWarning: /^[0-9]+([.]{0,1}[0-9]+){0,1}$/,
            CoordinateValue: /^(\-\+)?\\\d+(\.\\\d{1,6})?$/,
            CheckArea: /^(\-\+)?\\\d+(\.\\\d{1,2})?$/,
            ContactPhone: /^(1[0-9]{10})|(0\\\d{2}\\\d{8}(-\\\d{1,4})?)|(0\\\d{3}\\\d{7,8}(-\\\d{1,4})?)$/,
            personAge: /^\\\d+$/,
            MobilePhone: /^1[3|4|5|8|7|9][0-9]{9}$/,
            NumberDays: /^100$|^(\d|[1-9]\d)$/,
            ProductCoding: /^[A-Za-z0-9]+$/,
            PriceRegular: /^[0-9]+(.[0-9]{2})?$/,
            ContactPhone1: /^1\d{10}$/,
            ResidentIdentityCard: /^(\d{15}|\d{17}(\d|X))$/,
            ContactPhone2: /^1[34578]\d{9}$/,
            CheckValue: /^((\\s&&[^\\f\\n\\r\\t\\v])*)|(\\\d+)$/,
            ZhengShu: /^\d+$/,
            ContactPhone3: /^[0-9]+$/,
            ContactPhone4: /^1[0-9]{10}$/,
            regPassword: /(?!^[0-9]+$)(?!^[A-z]+$)(?!^[^A-z0-9]+$)^[^\s\u4e00-\u9fa5]{6,16}$/,
            regIDCard: /^(\d{15}|\d{17}(\d|X))$/,
            regFloat: /^(\-\+)?\\\d+(\.\\\d{1,2})?$/,
            priceFloat: /^([1-9]\d*|0)(\.\d{1,2})?$/,
            fuDainTwo: /^(([1-9][0-9]*)\\.([0-9]{2}))|[0]\\.([0-9]{2})$/,
            regFloat: /^(\-)?\d+(\.\d{1,2})?$/,
            regFloatPlus: /^\d+(\.\d{1,2})?$/,
            regEmail: /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
            reglocalRFID: /(?!^[0-9]+$)(?!^[A-z]+$)(?!^[^A-z0-9]+$)^[^\s\u4e00-\u9fa5]{24}$/,
            regclaCode:/^[\w\s]*$/,
            regFloat4Plus: /^\d+(\.\d{1,4})?$/,
        }
    },
    methods: {
        _ruleLength(num) {
            return {
                validator: function(rule, value, callback) {
                    var val = $.trim(value)
                    if (val.length > num) {
                        callback(new Error('长度不可超过' + num + '个字符'))
                    } else {
                        callback();
                    }
                },
                trigger: 'blur'
            }
        },
        _rulePassword() {
            return {
                validator: (function(rule, value, callback) {
                    var val = $.trim(value)
                    if (!this.regPassword.test(val)) {
                        callback(new Error('密码长度应为6-16位, 数字, 字母, 字符至少包含两种, 同时不能包含中文和空格'))
                    } else {
                        callback();
                    }
                }.bind(this)),
                trigger: 'blur'
            }
        },
        _ruleEmail() {
            return {
                validator: (function(rule, value, callback) {
                    var val = $.trim(value)
                    if (!this.regEmail.test(val)) {
                        callback(new Error('请输入正确的邮箱格式'))
                    } else {
                        callback();
                    }
                }.bind(this)),
                trigger: 'blur'
            }
        },
       _ruleExist(api, name, content) { //content  为传入对象，表示编辑情况下的数据对象
            return {
                validator: (function(rule, value, callback) {
                    let obj = {}
                    obj[rule.field] = value
                    if (content!=undefined) {
                        if (content[rule.field] === value) { //判断该字段是否更改过，未更改则不验证，更改则验证
                            callback();
                        } else {
                            this._ajax({
                                    url: api,
                                    param: obj
                                })
                                .then(function(d) {
                                    if (d.state != 0) {
                                        callback(new Error(name + '已存在'))
                                    } else {
                                        callback();
                                    }
                                })
                        }
                    }else{
                        this._ajax({
                                    url: api,
                                    param: obj
                                })
                                .then(function(d) {
                                    if (d.state != 0) {
                                        callback(new Error(name + '已存在'))
                                    } else {
                                        callback();
                                    }
                                })
                    }
                }.bind(this)),
                trigger: 'blur'
            }
        },
        _ruleMobile() {
            return {
                validator: (function(rule, value, callback) {
                    var val = $.trim(value)
                    if (val.length > 0 && !this.MobilePhone.test(val)) {
                        callback(new Error('请输入正确的手机号码'))
                    } else {
                        callback();
                    }
                }.bind(this)),
                trigger: 'blur'
            }
        },
        _ruleIDCard() {
            return {
                validator: (function(rule, value, callback) {
                    var val = $.trim(value)
                    if (val.length > 0 && !this.regIDCard.test(val)) {
                        callback(new Error('请输入正确的身份证号'))
                    } else {
                        callback();
                    }
                }.bind(this)),
                trigger: 'blur'
            }
        },
         _ruleLicensePlate() {
            return {
                validator: (function(rule, value, callback) {
                    var val = $.trim(value)
                    if (val.length > 0 && !this.LicensePlate.test(val)) {
                        callback(new Error('请输入正确的车牌号'))
                    } else {
                        callback();
                    }
                }.bind(this)),
                trigger: 'blur'
            }
        },
        _ruleTwoFloat() {
            return {
                validator: (function(rule, value, callback) {
                    var val = $.trim(value)
                    if (val.length > 0 && !this.regFloatPlus.test(val)) {
                        callback(new Error('请输入大于等于零的数字,最多保留两位小数'))
                    } else {
                        callback();
                    }
                }.bind(this)),
                trigger: 'blur'
            }
        },
        _ruleFourFloat() {
            return {
                validator: (function (rule, value, callback) {
                    var val = $.trim(value)
                    if (val.length > 0 && !this.regFloat4Plus.test(val)) {
                        callback(new Error('请输入大于等于零的数字,最多保留四位小数'))
                    } else {
                        callback();
                    }
                }.bind(this)),
                trigger: 'blur'
            }
        },
        _ruleRequired(name) {
            return {
                required: true,
                message: name + '不能为空'
            }
        },
        _ruleReg(reg, descript) { //通用验证正则表达式，reg 正则表达式  descript 错误描述
            return {
                validator: (function(rule, value, callback) {
                    var val = $.trim(value)
                    if (!reg.test(val)) {
                        callback(new Error(descript));
                    } else {
                        callback();
                    }
                }.bind(this)),
                trigger: 'blur'
            }
        },
		_ruleNumber(){
			return {
                validator: (function(rule, value, callback) {
                    var val = $.trim(value)
                    if (val.length > 0 && !this.ProductCoding.test(val)) {
                        callback(new Error('只能是字母与数字的组合'))
                    } else {
                        callback();
                    }
                }.bind(this)),
                trigger: 'blur'
            }
		},
        _ruleclaCode() {
            return {
                validator: (function(rule, value, callback) {
                    var val = $.trim(value)
                    if (val.length > 0 && !this.regclaCode.test(val)) {
                        callback(new Error('只能包含数字、字母、下划线'))
                    } else {
                        callback();
                    }
                }.bind(this)),
                trigger: 'blur'
            }
        },
    }
}