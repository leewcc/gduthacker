<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>入驻申请</title>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/AboutCollege.css">
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<jsp:include page="../../../Result/iehack.jsp"></jsp:include>
</head>
<body>
<jsp:include page="../HomePage/Nav.jsp"></jsp:include>
<div id="column_pic">
	<img src="/GDUTHackerSpace/Web/images/column/pictures1.png" alt="栏目图片"/>
	<div id="header_column">
		<div class="wrapper">
			<h2>主页 > 办事服务</h2>
			<h1>入驻申请</h1>
		</div>
	</div>
</div>
<div id="section" class="wrapper">
	<div id="sec_sidebar">
		<div id="sec_sidebar_head">
			<h2>办事服务</h2>
			<div id="sec_temp"></div>
		</div>
		<ul>
			<li class="current"><a href="#sec_article">入驻申请</a></li>
			<li><a href="/GDUTHackerSpace/Web/jsp/View/User/Services/ApplyForClass/ChooseClassrom.jsp">课室申请</a></li>

		</ul>
		<div id="sec_tempv1"></div>
	</div>
	<div id="sec_live_in">
		<div id="sec_live_in_container">
			<img src="/GDUTHackerSpace/Web/images/temp/LiveIn_03.png" alt="入驻申请流程图"/>
			<a id="miao_1">
				<div class="miao_right">
					<p>项目负责人在创新创业学院官网注册用户，填写在线填写项目基本信息，并下载打印《创新创业项目申报表》(该文件在页面下方)</p>
					<div class="miao_right_but"></div>
				</div>
				<span>1</span>
			</a>
			<a id="miao_2">
				<div class="miao_left">
					<p>填写《创新创业项目申报表》，并附《创业计划书》（仅针对创业项目）和相关证明材料。</p>
					<div class="miao_left_but"></div>
				</div>
				<span>2</span>
			</a>
			<a id="miao_3">
				<div class="miao_right">
					<p>将纸质材料递交到学院学生工作办公室，由学院统一审核盖章。（研究生团队需征得导师的同意）。</p>
					<div class="miao_right_but"></div>
				</div>
				<span>3</span>
			</a>
			<a id="miao_4" >
				<div class="miao_right">
					<p> 纸质材料统一送报创新创业学院后，学院对申报项目进行资格审查，并组织专家对申报项目材料进行初选。 </p>
					<div class="miao_right_but"></div>
				</div>
				<span>4</span>
			</a>
			<a id="miao_5" >	
				<div class="miao_left">
					<p> 初选合格后，创新创业学院每半年组织一次专家对初选合格学生进行答辩评审，并评选出优秀创客项目。</p>
					<div class="miao_left_but"></div>
				</div>
				<span>5</span>
			</a>
		 	<a id="miao_6" >	
				<div class="miao_left">
					<p> 优秀创客的项目将获得入驻平台资格，以及创新创科学院相关扶持。  </p>
					<div class="miao_left_but"></div>
				</div>
				<span>6</span>
			</a>
			<a id="miao_7">				
				<div class="miao_left">
					<p> 创客团队与创新创业学院签订《广东工业大学创新创业平台入驻协议》（创新项目为半年一签，创业项目为一年一签）。</p>
					<div class="miao_left_but"></div>
				</div>
				<span>7</span>
			</a>
			<a  id="miao_8">	
				<div class="miao_right">
					<p> 创客团队签订入驻协议后即入驻广东工业大学创新创业学院 。</p>
					<div class="miao_right_but"></div>
				</div>
				<span>8</span>
			</a>
			<a id="miao_9" >	
				<div class="miao_left">
					<p> 一轮入驻期满后，创客团队无违反规定行为并且运行良好的可优先提前一个月申请续签协议。或者办理退出手续。</p>
					<div class="miao_left_but"></div>
				</div>
				<span>9</span>
			</a> 
		</div>
		<a id="temp" href="/GDUTHackerSpace/apply/download?fileName=apply">下载《创新创业项目申报表》</a>
	</div>
</div>
<jsp:include page="../HomePage/Footer.jsp"></jsp:include>
<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-1.9.1.js"></script>
<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
</body>
</html>