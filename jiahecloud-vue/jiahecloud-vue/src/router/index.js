import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Test from '@/components/Test'
import Department from '@/components/Department'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    }, {
      path: '/test',
      name: 'Test',
      component: Test
    }, {
      path: '/dept',
      name: 'Department',
      component: Department
    }
  ]
})
