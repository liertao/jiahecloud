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
                <el-input placeholder="请输入用户名" v-model="deptInfo.name" clearable>
                    <el-button slot="append" icon="el-icon-search" @click="queryBtn"></el-button>
                </el-input>
            </el-col>
        </el-row>
        <el-row :gutter="20" style="margin-top:10rpx;">
            <el-col :span="1">
                <el-button type="primary" size="medium" @click="addDialogVisible=true">增加</el-button>
            </el-col>
            <el-col :span="1">
                <el-button type="warning" size="medium" @click="addDialogVisible=true" v-if="false">修改</el-button>
            </el-col>
            <el-col :span="1">
                <el-button type="danger" size="medium" @click="addDialogVisible=true" v-if="false">删除</el-button>
            </el-col>
        </el-row>
        <el-table :data="DeptList">
            <el-table-column label="序号" type="index" :span="3"></el-table-column>
            <el-table-column label="主键" prop="id" :span="3" v-if="false"></el-table-column>
            <el-table-column label="部门编码" prop="departmentCode" :span="3"></el-table-column>
            <el-table-column label="部门名称" prop="departmentName" :span="3"></el-table-column>
            <el-table-column label="操作" :span="3">
                <template slot-scope="scope">
                <el-button size="mini" @click="editDept(scope.$index, scope.row)">编辑</el-button>
                <el-button size="mini" type="danger" @click="delDept(scope.$index, scope.row.id)">删除</el-button>
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
        <el-form :model="addDeptForm" :rules="rules2" ref="addRuleForm" label-width="90px">
            <el-form-item label="部门编码">
                <el-input v-model="addDeptForm.departmentCode"></el-input>
            </el-form-item>
            <el-form-item label="部门名称">
                <el-input v-model="addDeptForm.departmentName"></el-input>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="addDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="addDept">确 定</el-button>
        </span>
        </el-dialog>

        <!-- 编辑 -->
        <el-dialog title="编辑" :visible.sync="editDialogVisible" width="30%">
        <el-form ref="form" :model="addDeptForm" label-width="90px">
            <el-form-item label="id" v-show="false">
            <el-input v-model="addDeptForm.id" ></el-input>
            </el-form-item>
            <el-form-item label="部门编码">
            <el-input v-model="addDeptForm.departmentCode" ></el-input>
            </el-form-item>
            <el-form-item label="部门名称">
            <el-input v-model="addDeptForm.departmentName" >
            </el-input>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="editDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="addDept">确 定</el-button>
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

<script>
const axios = require('axios')
export default {
  data () {
    return {
      radio: '1',
      deptInfo: {
        id: 0,
        departmentCode: '',
        departmentName: ''
      },
      DeptList: [],
      delDialogVisible: false,
      editDialogVisible: false,
      newUser: {
        name: '',
        birthday: '',
        address: ''
      },
      userIndex: 0,
      deptIndex: 0,
      addDialogVisible: false,
      addDeptForm: {
        id: 0,
        departmentCode: '',
        departmentName: ''
      },
      rules2: {
        departmentCode: [{
          required: true,
          message: '用户名不能为空',
          trigger: 'blur'
        }],
        departmentName: [{
          required: true,
          message: '密码不能为空',
          trigger: 'blur'
        }]
      }
    }
  },
  mounted () {
    // eslint-disable-next-line semi
    this.loadData();
  },
  methods: {
    loadData () {
      // eslint-disable-next-line semi
      let that = this;
      that.DeptList = []
      axios.post('/department/query', {
        data: 1
      }, {
        headers: {
          'Content-type': 'application/x-www-form-urlencoded'
        }
      }).then(function (res) {
        that.addDialogVisible = false
        that.editDialogVisible = false
        console.log(res)
        that.DeptList = res.data.root
        that.addDeptForm.id = 0
        that.addDeptForm.departmentCode = ''
        that.addDeptForm.departmentName = ''
      }).catch(function (error) {
        console.log(error)
      })
    },
    addDept () {
      let that = this
      let params = new URLSearchParams()
      params.append('id', that.addDeptForm.id)
      params.append('departmentCode', that.addDeptForm.departmentCode)
      params.append('departmentName', that.addDeptForm.departmentName)
      axios.post('/department/add', params
      ).then(function (res) {
        that.loadData()
      }).catch(function (error) {
        console.log(error)
      })
    },
    delDept (index, id) {
      let that = this
      this.$confirm('确认删除？')
        .then(_ => {
          let params = new URLSearchParams()
          params.append('id', id)
          axios.post('/department/delete', params
          ).then(function (res) {
            that.DeptList.splice(index, 1)
          }).catch(function (error) {
            console.log(error)
          })
        }).catch(_ => { })
    },
    editDept (index, item) {
      this.deptIndex = index
      this.addDeptForm = {
        id: item.id,
        departmentCode: item.departmentCode,
        departmentName: item.departmentName
      }
      this.editDialogVisible = true
    },
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
