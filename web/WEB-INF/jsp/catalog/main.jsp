<%--
  Created by IntelliJ IDEA.
  User: 40264
  Date: 2024/10/29
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/top.jsp"%>
<div id="Welcome">
    <div id="WelcomeContent">
       <!--显示登录用户name-->
    </div>
</div>

<div id="Main">
    <div id="Sidebar">
        <div id="SidebarContent">
            <a><img src="images/fish_icon.gif" /></a>
            Saltwater, Freshwater <br />
            <a><img src="images/dogs_icon.gif" /></a>
            Various Breeds <br />
            <a><img src="images/cats_icon.gif" /></a>
            Various Breeds, Exotic Varieties <br />
            <a><img src="images/reptiles_icon.gif" /></a>
            Lizards, Turtles, Snakes <br />
            <a><img src="images/birds_icon.gif" /></a>
            Exotic Varieties
        </div>
    </div>

    <div id="MainImage">
        <div id="MainImageContent">
            <map name="estoremap">
                <area alt="Birds" coords="72,2,280,250"
                      href="" shape="RECT" />
                <area alt="Fish" coords="2,180,72,250"
                      href="" shape="RECT" />
                <area alt="Dogs" coords="60,250,130,320"
                      href="" shape="RECT" />
                <area alt="Reptiles" coords="140,270,210,340"
                      href="" shape="RECT" />
                <area alt="Cats" coords="225,240,295,310"
                      href="" shape="RECT" />
                <area alt="Birds" coords="280,180,350,250"
                      href="" shape="RECT" />
            </map>
            <img height="355" src="images/splash.gif" usemap="#estoremap" width="350" /></div>
    </div>

    <div id="Separator">&nbsp;</div>
</div>

<%@ include file="../common/bottom.jsp"%>
