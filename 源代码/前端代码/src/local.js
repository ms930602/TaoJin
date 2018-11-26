import configs from './configs.js'
export default {
    set(key, value) {
        try {
            value = JSON.stringify(value);
        } catch (e) {
            value = value;
        }
        window.localStorage.setItem(configs.prefix + key, value);
    },
    get(key) {
        if (!key) {
            throw new Error('没有找到key。');
            return;
        }
        if (typeof key === 'object') {
            throw new Error('key不能是一个对象。');
            return;
        }
        var value = window.localStorage.getItem(configs.prefix + key);

        if (value !== null) {
            try {
                value = JSON.parse(value);
            } catch (e) {
                value = value;
            }
        }
        return value;
    },
    remove(key) {
        window.localStorage.removeItem(configs.prefix + key);
    },
    setSession(key, value) {
        try {
            value = JSON.stringify(value);
        } catch (e) {
            value = value;
        }
        window.sessionStorage.setItem(configs.prefix + key, value);
    },
    getSession(key) {
        if (!key) {
            throw new Error('没有找到key。');
            return;
        }
        if (typeof key === 'object') {
            throw new Error('key不能是一个对象。');
            return;
        }
        var value = window.sessionStorage.getItem(configs.prefix + key);
        if (value !== null) {
            try {
                value = JSON.parse(value);
            } catch (e) {
                value = value;
            }
        }
        return value;
    },
    checkpermission(menuName){
        var thisUser=this.get("userinfo")
        var check;
        var where = {
            longUserId:thisUser.userId,
            menuName:menuName
        }
        $.ajax({
            type: "POST",
            url: ROOT_API + '/api/user/user/checkMenum',
            data: where,
            async:false,
            dataType: "json",
            success: function(data){
                check=data.aaData;
            }
        });
        return check;
    },
    removeSession(key) {
        window.sessionStorage.removeItem(configs.prefix + key);
    },
    removeRedis(){
            $.ajax({
                type: "POST",
                url: ROOT_API + '/api/user/user/removeRedis',
                data: {longUserId:this.get("userinfo").userId},
                async:false,
                dataType: "json",
                success: function(data){
                    
                }
        });
    }
}