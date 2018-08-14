#!groovy

// 需要预先在 Jenkins 系统管理 ->  系统设置 -> Global Pipeline Libraries 中配置好 shared libraries
// 引入 shared-lib
//@Library('shared-lib@master') _

properties([
        parameters([string(defaultValue: 'master', description: 'LIB_VERSION', name: 'LIB_VERSION', trim: false),
                    string(defaultValue: 'openresty/nginx/conf/vhosts/corp_product.conf', description: 'nginxConfigLocation', name: 'nginxConfigLocation', trim: false)])])


library identifier: "shared-lib@${params.LIB_VERSION}", retriever:
        modernSCM(github(credentialsId: 'username-password-for-github',
                id: '4df012b9-0dfa-497a-9b88-8afcbb0ced1e',
                repoOwner: 'chenjianzhao', repository: 'shared-lib',
                traits: [[$class: 'org.jenkinsci.plugins.github_branch_source.BranchDiscoveryTrait', strategyId: 1],
                         [$class: 'org.jenkinsci.plugins.github_branch_source.OriginPullRequestDiscoveryTrait', strategyId: 1],
                         [$class: 'org.jenkinsci.plugins.github_branch_source.ForkPullRequestDiscoveryTrait', strategyId: 1, trust: [$class: 'TrustPermission']]])
        )

// 执行外部的 pipeline
loadExtPipeline()