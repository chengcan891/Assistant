package com.chengcan.assistant

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.chengcan.base.BaseFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.yanzhenjie.permission.Action
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.runtime.Permission
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {

        Log.i("MainActivity", "onCreate")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return

        // Set up Action Bar
        val navController = host.navController

//        appBarConfiguration = AppBarConfiguration(navController.graph)

        val drawerLayout: DrawerLayout? = findViewById(R.id.drawer_layout)
        //配置不需要返回按钮的fragment
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.diary, R.id.languagemodule_main, R.id.android),
            drawerLayout
        )

        setupActionBar(navController, appBarConfiguration)


        setupBottomNavMenu(navController)

        setupNavigationMenu(navController)

//        testJavaCrash()

    }

    fun testJavaCrash() {
        val i = 1 / 0
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

       //        Log.i("MainActivity", "analyticsService"+analyticsService)
        AndPermission.with(this)
            .runtime()
            .permission(Permission.Group.STORAGE)
//            .permission(WRITE_EXTERNAL_STORAGE)
//            .permission(READ_EXTERNAL_STORAGE)
            .onGranted(Action<List<String?>> { permissions: List<String?>? -> })
            .onDenied(Action<List<String?>> { permissions: List<String?>? ->


                finish()

            })
            .start()

    }

    override fun onBackPressed() {

        Log.i("MainActivity", "onBackPressed in")

        if(!back()){
            Log.i("MainActivity", "onBackPressed getFragment in")
            super.onBackPressed()
        }

        Log.i("MainActivity", "onBackPressed out")
    }


    /**
     * 当前fragment执行back事件，暂时发现navHostFragment.childFragmentManager.fragments只有一个，就是当前fragment
     */
    fun  back(): Boolean{
        val navHostFragment = this.supportFragmentManager.fragments.first() as NavHostFragment

        Log.i("MainActivity", "getFragment"+  navHostFragment.childFragmentManager.fragments.size)

        navHostFragment.childFragmentManager.fragments.forEach {
            Log.i("MainActivity", it.toString())
            return if(it is BaseFragment) it.onBackPressed() else false
        }

        return false
    }

    /**
     * toolbar返回按钮功能实现
     */
    override fun onSupportNavigateUp(): Boolean {
        Log.i("MainActivity","onSupportNavigateUp")
        return findNavController(R.id.nav_host_fragment).navigateUp()
    }

    private fun setupActionBar(
        navController: NavController,
        appBarConfig: AppBarConfiguration
    ) {
        setupActionBarWithNavController(navController, appBarConfig)
    }

    private fun setupNavigationMenu(navController: NavController) {
        val sideNavView = findViewById<NavigationView>(R.id.nav_view)
        sideNavView?.setupWithNavController(navController)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        bottomNav?.setupWithNavController(navController)
    }
}