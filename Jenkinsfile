#!groovy

// 需要预先在 Jenkins 系统管理 ->  系统设置 -> Global Pipeline Libraries 中配置好 shared libraries
// 引入 shared-lib
//@Library('shared-lib@master') _

// 执行外部的 pipeline
pipeline {

    parameters {
        string defaultValue: 'master', description: 'shared library 分支', name: 'LIB_VERSION', trim: true
        string defaultValue: 'openresty/nginx/conf/vhosts/corp_product.conf', description: 'nginxConfigLocation', name: 'nginxConfigLocation', trim: true
        choice choices: ['none', 'jgitflow:release-start', 'jgitflow:release-finish'], description: '', name: 'jgitflowFlag'
    }

    libraries {
        lib('shared-lib@${params.LIB_VERSION}')
    }

    loadExtPipeline()
}
