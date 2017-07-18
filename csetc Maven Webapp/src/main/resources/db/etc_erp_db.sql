|-- 插入班级管理记录
insert into tb_sys_permission 
	(PERMISSIONID, PERMISSIONNAME, ACTIONURL, LEVEL, ORDERNUM, PERMISSIONDESC, CREATETIME, STATUS)
 values ('20170713000001', '班级管理', '..', 	   '-1',    '5',	'班级的相关操作',now(), '1') 

-- 客户入班
insert into tb_sys_permission
	(PERMISSIONID, PERMISSIONNAME, ACTIONURL, LEVEL, ORDERNUM, PERMISSIONDESC, CREATETIME, STATUS)
values ('20170713000002', '客户入班', 'apply/query','20170713000001','51','销售申请学员入班',now(),'1')

-- 客户入班控制器名字的修改
tb_sys_sequencetb_sys_permissiontb_sys_permissiontb_sys_permission

-- TB_CUSTOMER_ATTACHMENT 客户资料表
-- 首先，在 TB_SYS_BASECODE 基础代码表插入'附件类型'大类，需要外键约束
insert into tb_sys_basecode (ID, LEVEL1ID, LEVEL1NAME, LEVEL2ID, LEVEL2NAME, STATUS)
values ('20170713000001', '09', '附件类型', '1', '评测报告扫描件', '1');

-- 发现问题，插入的数据并没有成功显示
select * from tb_sys_basecode;
-- 发现数据插入错误，因为sqlyog默认显示了50条

-- 更新数据
update tb_sys_basecode set ID='20170713000001', LEVEL1ID='22' where ID='20170713000009';
update tb_sys_basecode set ID='20170713000002', LEVEL1ID='22', LEVEL2NAME='身份证正面扫描件' where ID='20170713000010';


-- 发现页面还是没有显示'附件类型'大类，数了大类数后比22还多
select * from tb_sys_basecode order by LEVEL1ID desc;

-- 更改序列号(level1id)
update tb_sys_basecode set LEVEL1ID='30' where ID='20170713000001';
update tb_sys_basecode set LEVEL1ID='30' where ID='20170713000002';

update tb_sys_basecode set LEVEL2ID='2' where ID='20170713000002';


-- TB_CUSTOMER_ATTACHMENT 客户资料表创建
/*
表  名	TB_CLASSSTUINFO
字段名	中文说明	类型	长度	必填	主键	格式或默认值
ID	序号		VARCHAR	14	yes	yes	
CUSTOMERID客户编号	VARCHAR	14	yes		
ATYPE	附件类型	VARCHAR	2	yes		选择框
AMEMO	附件说明	VARCHAR	500			
AURL	附件地址	VARCHAR	100			
CREATOR	创建人		VARCHAR	14	yes		
CREATETIME创建时间	DATETIME	yes		
STATUS	状态	VARCHAR	1			默认为1
STATUS：0 无效 1 有效
ATYPE：附件类型应该存储在TB_SYS_BASECODE中
LEVEL1NAME名称为附件类型，LEVEL2NAME分别为：评测报告扫描件|身份证正面扫描件|学历证书扫描件|个人信息表扫描件|学生结业证书，注意，对应的VALUE如果设置为1，则表示该附件为必须上传类型，为空或者0，则表示可传可不传，在前端页面需要加强校验
注意：结业证书很重要，是班级能否结业的重要检测附件，班级不能结业的学员在结业之前就应该分流出去，比如转班|休学等。结业是批量操作的。

*/
create table tb_customer_attachment (
	ID varchar(14) not null comment '附件序号',
	CUSTOMERID varchar(14) not null comment '客户',
	ATYPE varchar(2) not null comment '附件类型',
	AMEMO varchar(500) comment '附件说明',
	AURL varchar(100) comment '附件地址',
	CREATOR varchar(14) not null comment '创建人',
	CREATETIME datetime not null comment '创建时间',
	STATUS varchar(1) default '1' comment '状态',
	primary key(ID),
	foreign key(CREATOR) references tb_sys_user(USERID)
	#,foreign key(ATYPE) references tb_sys_basecode(LEVEL2ID)
);

drop table tb_customer_attachment;
	