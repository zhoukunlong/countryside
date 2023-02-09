--村庄信息表
CREATE TABLE tb_country (
	id varchar(255) NOT NULL,
	country_name varchar(255) NOT NULL,
	create_time varchar(255) NOT NULL,
	update_time varchar(255) NOT NULL,
	is_delete int4 NOT NULL,
	group_id varchar(255) NOT NULL,
	country_desc text NULL,
	country_age int4 NULL DEFAULT 0,
	expire_time varchar(255) NOT NULL,
	country_status int4 NULL DEFAULT 0,
	CONSTRAINT tb_country_pk PRIMARY KEY (id)
);

-- Column comments

/*COMMENT ON COLUMN tb_country.id IS '主键';
COMMENT ON COLUMN tb_country.country_name IS '村庄名称';
COMMENT ON COLUMN public.tb_country.create_time IS '创建时间';
COMMENT ON COLUMN public.tb_country.update_time IS '修改时间';
COMMENT ON COLUMN public.tb_country.is_delete IS '是否删除标识 0：否  1：是';
COMMENT ON COLUMN public.tb_country.group_id IS '分组id';
COMMENT ON COLUMN public.tb_country.country_desc IS '乡村描述';
COMMENT ON COLUMN public.tb_country.country_age IS '村庄年龄';
COMMENT ON COLUMN public.tb_country.expire_time IS '过期时间';
COMMENT ON COLUMN public.tb_country.country_status IS '村庄状态 0：未发布 1：已发布';*/

--用户信息表
CREATE TABLE tb_user (
	id varchar(255) NOT NULL,
	user_name varchar(255) NOT NULL,
	account varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
	birth_time varchar(255) NOT NULL,
	is_delete int4 NOT NULL,
	country_id varchar(255) NULL,
	user_sex int4 NOT NULL,
	create_time varchar(255) NOT NULL,
	update_time varchar(255) NOT NULL,
	user_status int4 NOT NULL,
	user_tel varchar(255) NULL,
	user_no varchar(255) NULL,
	user_desc text NULL,
	user_img varchar(255) NULL,
	user_resource int4 NOT NULL,
	expire_time varchar(255) NULL,
	other varchar(255) NULL,
	CONSTRAINT tb_user_pk PRIMARY KEY (id)
);

-- Column comments

/*COMMENT ON COLUMN public.tb_user.id IS '主键';
COMMENT ON COLUMN public.tb_user.user_name IS '用户名';
COMMENT ON COLUMN public.tb_user.account IS '登入账号';
COMMENT ON COLUMN public.tb_user."password" IS '登入密码';
COMMENT ON COLUMN public.tb_user.birth_time IS '出生日期';
COMMENT ON COLUMN public.tb_user.is_delete IS '是否删除标识 0：否  1：是';
COMMENT ON COLUMN public.tb_user.country_id IS '所属村庄';
COMMENT ON COLUMN public.tb_user.user_sex IS '性别 0:男  1：女';
COMMENT ON COLUMN public.tb_user.create_time IS '创建时间';
COMMENT ON COLUMN public.tb_user.update_time IS '修改时间';
COMMENT ON COLUMN public.tb_user.user_status IS '用户状态 0：生  1：死';
COMMENT ON COLUMN public.tb_user.user_tel IS '用户手机号码';
COMMENT ON COLUMN public.tb_user.user_no IS '用户身份证号';
COMMENT ON COLUMN public.tb_user.user_desc IS '用户描述';
COMMENT ON COLUMN public.tb_user.user_img IS '用户头像';
COMMENT ON COLUMN public.tb_user.user_resource IS '用户来源 0：用户注册  1：他人增加';
COMMENT ON COLUMN public.tb_user.expire_time IS '过期时间';
COMMENT ON COLUMN public.tb_user.other IS '其他信息';*/


--角色信息表
CREATE TABLE tb_role (
	id varchar(255) NOT NULL,
	role_name varchar(255) NOT NULL,
	create_time varchar(255) NOT NULL,
	update_time varchar(255) NOT NULL,
	is_delete int4 NOT NULL,
	CONSTRAINT tb_role_pk PRIMARY KEY (id)
);

-- Column comments

/*COMMENT ON COLUMN public.tb_role.id IS '主键';
COMMENT ON COLUMN public.tb_role.role_name IS '角色名称';
COMMENT ON COLUMN public.tb_role.create_time IS '创建时间';
COMMENT ON COLUMN public.tb_role.update_time IS '修改时间';
COMMENT ON COLUMN public.tb_role.is_delete IS '是否删除标识 0：否  1：是';*/

--地市级联表
CREATE TABLE tb_region_tree (
	id varchar(255) NOT NULL,
	region_name varchar(255) NOT NULL,
	create_time varchar(255) NOT NULL,
	update_time varchar(255) NOT NULL,
	is_delete int4 NOT NULL,
	layer int4 NOT NULL,
	is_leaf int4 NOT NULL,
	parent_id varchar(255) NOT NULL,
	region_code varchar(255) NULL,
	CONSTRAINT tb_region_tree_pk PRIMARY KEY (id)
);

-- Column comments

/*COMMENT ON COLUMN public.tb_region_tree.id IS '主键';
COMMENT ON COLUMN public.tb_region_tree.region_name IS '地市名称';
COMMENT ON COLUMN public.tb_region_tree.create_time IS '创建时间';
COMMENT ON COLUMN public.tb_region_tree.update_time IS '修改时间';
COMMENT ON COLUMN public.tb_region_tree.is_delete IS '是否删除 0：否 1：是';
COMMENT ON COLUMN public.tb_region_tree.layer IS '层级';
COMMENT ON COLUMN public.tb_region_tree.is_leaf IS '是否是叶子节点 0：否 1：是';
COMMENT ON COLUMN public.tb_region_tree.parent_id IS '父节点';
COMMENT ON COLUMN public.tb_region_tree.region_code IS '地市编码';*/

--族谱信息表
CREATE TABLE tb_pedigree (
	id varchar(255) NOT NULL,
	pedigree_name varchar(255) NOT NULL,
	country_id varchar(255) NOT NULL,
	create_time varchar(255) NOT NULL,
	update_time varchar(255) NOT NULL,
	is_delete int4 NOT NULL,
	pedigree_desc text NULL,
	pedigree_img varchar(255) NULL,
	startIndex varchar(255) NULL,
	CONSTRAINT tb_pedigree_pk PRIMARY KEY (id)
);
/*COMMENT ON TABLE public.tb_pedigree IS '族谱信息表';

-- Column comments

COMMENT ON COLUMN public.tb_pedigree.id IS '主键';
COMMENT ON COLUMN public.tb_pedigree.pedigree_name IS '族谱名称';
COMMENT ON COLUMN public.tb_pedigree.country_id IS '所属村庄';
COMMENT ON COLUMN public.tb_pedigree.create_time IS '创建时间';
COMMENT ON COLUMN public.tb_pedigree.update_time IS '修改时间';
COMMENT ON COLUMN public.tb_pedigree.is_delete IS '是否删除标识  0：否  1：是';
COMMENT ON COLUMN public.tb_pedigree.pedigree_desc IS '族谱描述';
COMMENT ON COLUMN public.tb_pedigree.pedigree_img IS '族谱图片';*/


--族谱树
CREATE TABLE tb_pedigree_tree (
	id varchar(255) NOT NULL,
	user_id varchar(255) NOT NULL,
	country_id varchar(255) NOT NULL,
	parent_id varchar(255) NOT NULL,
	create_time varchar(255) NOT NULL,
	update_time varchar(255) NOT NULL,
	layer int4 NULL,
	route_path varchar(10000),
	is_delete int4 NOT NULL
);
/*COMMENT ON TABLE public.tb_pedigree_tree IS '族谱树';

-- Column comments

COMMENT ON COLUMN public.tb_pedigree_tree.id IS '主键';
COMMENT ON COLUMN public.tb_pedigree_tree.user_id IS '用户id';
COMMENT ON COLUMN public.tb_pedigree_tree.country_id IS '村庄id';
COMMENT ON COLUMN public.tb_pedigree_tree.parent_id IS '父节点id';
COMMENT ON COLUMN public.tb_pedigree_tree.pedigree_id IS '关联族谱id';
COMMENT ON COLUMN public.tb_pedigree_tree.create_time IS '创建时间';
COMMENT ON COLUMN public.tb_pedigree_tree.update_time IS '修改时间';
COMMENT ON COLUMN public.tb_pedigree_tree.wife_id IS '配偶id';
COMMENT ON COLUMN public.tb_pedigree_tree.layer IS '层级';
COMMENT ON COLUMN public.tb_pedigree_tree.is_delete IS '是否删除标识 0：否 1：是';*/

--用户角色关联表
CREATE TABLE tb_user_role (
	id varchar(255) NOT NULL,
	user_id varchar(255) NOT NULL,
	role_id varchar(255) NOT NULL,
	relat_name varchar(255) NULL,
	create_time varchar(255) NOT NULL,
	update_time varchar(255) NOT NULL,
	country_id varchar(255) NOT NULL,
	is_delete int4 NOT NULL
);
/*COMMENT ON TABLE public.tb_user_role IS '用户角色关联表';

-- Column comments

COMMENT ON COLUMN public.tb_user_role.id IS '主键id';
COMMENT ON COLUMN public.tb_user_role.user_id IS '用户id';
COMMENT ON COLUMN public.tb_user_role.role_id IS '角色id';
COMMENT ON COLUMN public.tb_user_role.relat_name IS '角色名称描述';
COMMENT ON COLUMN public.tb_user_role.country_id IS '村庄名称';
COMMENT ON COLUMN public.tb_user_role.create_time IS '创建时间';
COMMENT ON COLUMN public.tb_user_role.update_time IS '修改时间';
COMMENT ON COLUMN public.tb_user_role.is_delete IS '是否删除标识 0：否  1：是';*/

--审批工单表
CREATE TABLE tb_process (
	id varchar(255) NOT NULL,
	process_title varchar(255) NOT NULL,
	process_content varchar(255) NOT NULL,
	status int4 NOT NULL DEFAULT 0,
	user_id varchar(255) NOT NULL,
	approve_role_id varchar(255) NOT NULL,
	country_id varchar(255) NOT NULL,
	create_time varchar(255) NOT NULL,
	update_time varchar(255) NOT NULL,
	is_delete int4 NOT NULL,
	CONSTRAINT tb_process_pk PRIMARY KEY (id)
);
/*COMMENT ON TABLE public.tb_process IS '审核工单表';

-- Column comments

COMMENT ON COLUMN public.tb_process.id IS '主键';
COMMENT ON COLUMN public.tb_process.process_title IS '申请标题';
COMMENT ON COLUMN public.tb_process.process_content IS '申请内容';
COMMENT ON COLUMN public.tb_process.status IS '申请状态 0：审批中 1：审批通过 2：审批不通过';
COMMENT ON COLUMN public.tb_process.user_id IS '申请人';
COMMENT ON COLUMN public.tb_process.approve_role_id IS '审批角色';
COMMENT ON COLUMN public.tb_process.country_id IS '村庄id';
COMMENT ON COLUMN public.tb_process.create_time IS '创建时间';
COMMENT ON COLUMN public.tb_process.update_time IS '修改时间';*/

