<template>
    <div id="app">
        <!-- <el-row :gutter="20">
            <el-col :span="8">
                <el-input v-model="userInfo.name" placeholder="请输入姓名"></el-input>
            </el-col>
            <el-col :span="5">
                <el-date-picker v-model="userInfo.birthday" placeholder="选择生日" format="yyyy年MM月dd日" value-format="yyyy-MM-dd">
                </el-date-picker>
            </el-col>
            <el-col :span="11">
                <el-input v-model="userInfo.address" placeholder="请输入地址"></el-input>
            </el-col>
            </el-row>
            <el-row :gutter="20" style="margin-top: 10px;">
            <el-col :span="24">
                <el-button type="primary" @click="addUser">增加</el-button>
            </el-col>
        </el-row> -->

        <el-row :gutter="20">
            <el-col :span="6">
                <el-input placeholder="请输入用户名" v-model="userInfo.name" clearable>
                    <el-button slot="append" icon="el-icon-search" @click="queryBtn"></el-button>
                </el-input>
            </el-col>
        </el-row>
        <el-row :gutter="20" style="margin-top:10rpx;">
            <el-col :span="1"><!--@click="addUser"-->
                <el-button type="primary" size="medium" @click="addUser">增加</el-button>
                <!-- <el-button type="primary" size="medium" @click="addDialogVisible=true">增加</el-button> -->
            </el-col>
            <el-col :span="1">
                <el-button type="warning" size="medium" @click="addDialogVisible=true">修改</el-button>
            </el-col>
            <el-col :span="1">
                <el-button type="danger" size="medium" @click="addDialogVisible=true">删除</el-button>
            </el-col>
        </el-row>
        <el-table :data="UserList">
            <el-table-column label="序号" type="index"></el-table-column>
            <el-table-column label="姓名" prop="name" width="200"></el-table-column>
            <el-table-column label="生日" prop="birthday" width="200"></el-table-column>
            <el-table-column label="地址" prop="address"></el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                <el-button size="mini" @click="editUser(scope.$index, scope.row)">编辑</el-button>
                <el-button size="mini" type="danger" @click="delUser(scope.$index)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-pagination
            background
            layout="prev, pager, next"
            :total="1000">
        </el-pagination>

        <!--新增弹窗-->
        <el-dialog
            center
            title="新增"
            :visible.sync="addDialogVisible"
            width="30%"
            >
        <el-form :model="addUserForm" ref="addRuleForm" label-width="90px">
            <el-form-item label="用户名">
                <el-input v-model="addUserForm.name"></el-input>
            </el-form-item>
            <el-form-item label="昵称">
                <el-input v-model="addUserForm.nickName"></el-input>
            </el-form-item>
            <el-form-item label="手机号码">
                <el-input v-model="addUserForm.mobile"></el-input>
            </el-form-item>
            <el-form-item label="邮箱">
                <el-input v-model="addUserForm.email"></el-input>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="addDialogVisible = false">取 消</el-button>
            <el-button type="primary">确 定</el-button>
        </span>
        </el-dialog>

        <!-- 编辑 -->
        <el-dialog title="编辑" :visible.sync="editDialogVisible">
        <el-form ref="form" :model="newUser" label-width="60px">
            <el-form-item label="姓名">
            <el-input v-model="newUser.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="生日">
            <el-date-picker v-model="newUser.birthday" placeholder="选择生日" format="yyyy年MM月dd日" value-format="yyyy-MM-dd">
            </el-date-picker>
            </el-form-item>
            <el-form-item label="地址">
            <el-input v-model="newUser.address" autocomplete="off"></el-input>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="editDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="saveUser">确 定</el-button>
        </div>
        </el-dialog>
        <!-- 删除 -->
        <el-dialog title="提示" :visible.sync="delDialogVisible" width="30%">
        <span slot="footer" class="dialog-footer">
            <el-button @click="delDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="delDialogVisible = false">确 定</el-button>
        </span>
        </el-dialog>
    </div>
</template>

<script src="./modules/Vue/vue.js" type="text/javascript" charset="utf-8"></script>
<script src="./modules/Axios/axios.js" type="text/javascript" charset="utf-8"></script>
<script src="./modules/Element-ui/index.js" type="text/javascript" charset="utf-8"></script>
<script>
export default {
  data () {
    return {
      radio: '1',
      userInfo: {
        name: '',
        birthday: '',
        address: ''
      },
      UserList: [],
      delDialogVisible: false,
      editDialogVisible: false,
      newUser: {
        name: '',
        birthday: '',
        address: ''
      },
      userIndex: 0,
      //第二种
      addDialogVisible: false,
      addUserForm: {
        name: '',
        nickName: '',
        email: '',
        mobile: ''
      }
    }
  },
  methods: {
    addUser () {
      this.UserList.push(this.userInfo)
      this.userInfo = {
        name: '',
        birthday: '',
        address: ''
      }
    },
    editUser (index, item) {
      this.userIndex = index
      this.newUser = {
        name: item.name,
        birthday: item.birthday,
        address: item.address
      }
      this.editDialogVisible = true
    },
    delUser (index) {
      this.$confirm('确认删除？')
        .then(_ => {
          this.UserList.splice(index, 1)
        }).catch(_ => { })
    },
    saveUser () {
      this.editDialogVisible = false
      // eslint-disable-next-line no-undef
      Vue.set(this.UserList, this.userIndex, this.newUser)
    }
  }
}
</script>
