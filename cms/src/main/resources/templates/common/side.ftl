<aside class="main-sidebar">

    <section class="sidebar">
        <ul class="sidebar-menu">
            <#if username == 'admin'>
            <li id="page_index">
                <a href="/index">
                    <i class="fa fa-dashboard"></i> <span>首页</span>
                </a>
            </li>
            <li id="page_users">
                <a href="/index/users">
                    <i class="fa fa-folder"></i> <span>用户管理</span>
                </a>
            </li>
            </#if>
        </ul>
    </section>

</aside>