# 审核管理
update tb_sys_permission set ORDERNUM='7' where PERMISSIONID='20170718000001';
update tb_sys_permission set ORDERNUM='71' where PERMISSIONID='20170718000002';
update tb_sys_permission set ORDERNUM='72' where PERMISSIONID='20170718000003';
update tb_sys_permission set ORDERNUM='73' where PERMISSIONID='20170718000004';

insert into tb_sys_permission(PERMISSIONID, PERMISSIONNAME, ACTIONURL, LEVEL, ORDERNUM, PERMISSIONDESC, CREATETIME, STATUS)
values('20170721000001', '审核管理', '..', '-1', '6', '审核', now(), '1');
insert into tb_sys_permission(PERMISSIONID, PERMISSIONNAME, ACTIONURL, LEVEL, ORDERNUM, PERMISSIONDESC, CREATETIME, STATUS)
values('20170721000002', '客户资质', 'CustomerQualification/query.do',  '20170721000001', '61', '客户资质审核', now(), '1');