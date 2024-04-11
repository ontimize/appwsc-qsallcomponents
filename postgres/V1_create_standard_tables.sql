-- public.public.accounttypes definition

-- drop table

 drop table if exists accounttypes;

create table accounttypes (
	accounttypeid integer not null generated by default as identity (start with 1),
	accounttypename varchar(100) not null,
	constraint sys_pk_10456 primary key (accounttypeid)
);

-- public.public.branches definition

-- drop table

 drop table if exists branches;

create table branches (
	officeid varchar(50) not null,
	name varchar(50),
	address varchar(255),
	startdate timestamp,
	details varchar(10485760),
	phone varchar(50),
	address_en_us varchar(255),
	address_es_es varchar(255),
	address_gl_es varchar(255),
	name_en_us varchar(255),
	name_es_es varchar(255),
	name_gl_es varchar(255),
	officexml varchar(10485760),
	branchplan bytea,
	branch_plan varchar(256),
	maxlongitude double precision,
	minlongitude double precision,
	maxlatitude double precision,
	minlatitude double precision,
	geometrylayout bytea,
	country varchar(100),
	constraint sys_pk_10143 primary key (officeid)
);

-- public.public.customertypes definition

-- drop table

 drop table if exists customertypes;

create table customertypes (
	customertypeid integer not null generated by default as identity (start with 1),
	description varchar(255),
	description_en_us varchar(255),
	description_es_es varchar(255),
	description_gl_es varchar(255),
	constraint sys_pk_10120 primary key (customertypeid)
);

-- public.public.employeetypes definition

-- drop table

 drop table if exists employeetypes;

create table employeetypes (
	employeetypeid integer not null generated by default as identity (start with 1),
	employeetypename varchar(50) not null,
	employeetypedesc varchar(10485760),
	constraint sys_pk_10180 primary key (employeetypeid)
);

-- public.public.movementtypes definition

-- drop table

 drop table if exists movementtypes;

create table movementtypes (
	movementtypeid integer not null generated by default as identity (start with 1),
	description varchar(50),
	description_en_us varchar(50),
	description_es_es varchar(50),
	description_gl_es varchar(50),
	constraint sys_pk_10139 primary key (movementtypeid)
);

-- public.public.preferences definition

-- drop table

 drop table if exists preferences;

create table preferences (
	id integer not null generated by default as identity (start with 1),
	name varchar(255),
	description varchar(255),
	preferences varchar(5000),
	entity varchar(100),
	"type" bit,
	constraint sys_pk_10494 primary key (id)
);

-- public.public.reports definition

-- drop table

 drop table if exists reports;

create table reports (
	id integer not null generated by default as identity (start with 1),
	"uuid" varchar(255) not null,
	name varchar(255),
	description varchar(255),
	report_type varchar(255),
	main_report_filename varchar(255) not null,
	zip bytea,
	compiled bytea,
	constraint sys_pk_10469 primary key (id)
);

-- public.public.tasks definition

-- drop table

 drop table if exists tasks;

create table tasks (
	id integer not null generated by default as identity (start with 1),
	"uuid" varchar(255) not null,
	"result" bytea,
	constraint sys_pk_10484 primary key (id)
);

-- public.public.tconfigs definition

-- drop table

 drop table if exists tconfigs;

create table tconfigs (
	id_config integer not null generated by default as identity (start with 1),
	user_config varchar(255),
	type_config varchar(255),
	components varchar(10485760),
	constraint sys_pk_10490 primary key (id_config)
);

-- public.public.tdms_doc definition

-- drop table

 drop table if exists tdms_doc;

create table tdms_doc (
	id_dms_doc integer not null generated by default as identity (start with 1),
	update_date date,
	update_by_id integer,
	doc_name varchar(255) not null,
	owner_id integer not null,
	doc_description text,
	doc_keywords varchar(255),
	constraint tdms_doc_pk primary key (id_dms_doc)
);

-- public.public.tformprovider definition

-- drop table

 drop table if exists tformprovider;

create table tformprovider (
	id_formprovider integer not null generated by default as identity (start with 1),
	formname varchar(10485760) not null,
	formxml varchar(10485760) not null,
	constraint tformprovider_pk primary key (id_formprovider)
);

-- public.public.ti18n definition

-- drop table

 drop table if exists ti18n;

create table ti18n (
	id_i18n integer not null generated by default as identity (start with 1),
	class_name varchar(150),
	i18n_description varchar(250),
	constraint sys_pk_10206 primary key (id_i18n)
);

-- public.public.ti18n_value definition

-- drop table

 drop table if exists ti18n_value;

create table ti18n_value (
	id_i18n_value integer not null generated by default as identity (start with 1),
	id_i18n integer not null,
	"key" varchar(250),
	es_es varchar(10485760),
	en_us varchar(10485760),
	es_es_gl varchar(10485760),
	constraint sys_pk_10210 primary key (id_i18n_value)
);

-- public.public.trequest_statistics definition

-- drop table

 drop table if exists trequest_statistics;

create table trequest_statistics (
	id_request_statistics integer not null,
	service_name varchar(255),
	method_name varchar(50),
	user_name varchar(50),
	execution_date date,
	execution_time integer,
	method_params varchar(5000),
	service_exception varchar(5000),
	constraint sys_pk_10194 primary key (id_request_statistics)
);

-- public.public.trole definition

-- drop table

 drop table if exists trole;

create table trole (
	id_rolename integer not null generated by default as identity (start with 1),
	rolename varchar(255),
	xmlclientpermission varchar(10485760),
	constraint sys_pk_10096 primary key (id_rolename)
);

-- public.public.tserver_permission definition

-- drop table

 drop table if exists tserver_permission;

create table tserver_permission (
	id_server_permission integer not null generated by default as identity (start with 1),
	permission_name varchar(10485760),
	constraint sys_pk_10108 primary key (id_server_permission)
);

-- public.public.tsetting definition

-- drop table

 drop table if exists tsetting;

create table tsetting (
	id_setting integer not null generated by default as identity (start with 1),
	setting_key varchar(10485760),
	setting_value varchar(10485760),
	setting_comment varchar(10485760),
	constraint sys_pk_10198 primary key (id_setting)
);

-- public.public.tshare definition

-- drop table

 drop table if exists tshare;

create table tshare (
	id_share integer not null generated by default as identity (start with 1),
	message varchar(10485760),
	share_type varchar(10485760) not null,
	content_share varchar(10485760) not null,
	user_source varchar(50) not null,
	name varchar(50) not null,
	constraint sys_pk_10278 primary key (id_share)
);

-- public.public.tshare_target definition

-- drop table

 drop table if exists tshare_target;

create table tshare_target (
	id_share_target integer not null generated by default as identity (start with 1),
	id_share integer not null,
	user_target varchar(50) not null,
	constraint sys_pk_10290 primary key (id_share_target)
);

-- public.public.tuser definition

-- drop table

 drop table if exists tuser;

create table tuser (
	user_ varchar(50) not null,
	password varchar(50),
	name varchar(50),
	surname varchar(50),
	email varchar(50),
	nif varchar(50),
	userblocked timestamp,
	lastpasswordupdate timestamp default current_timestamp,
	firstlogin boolean default true,
	constraint sys_pk_10092 primary key (user_)
);

-- public.public.tuser_preference definition

-- drop table

 drop table if exists tuser_preference;

create table tuser_preference (
	id_user_preference integer not null generated by default as identity (start with 1),
	preference_name varchar(150),
	user_login varchar(150),
	preference_value varchar(10485760),
	constraint sys_pk_10202 primary key (id_user_preference)
);

-- public.public.accounts definition

-- drop table

 drop table if exists accounts;

create table accounts (
	accountid integer not null generated by default as identity (start with 1),
	entityid varchar(50) not null,
	officeid varchar(50) not null,
	cdid varchar(50),
	anid varchar(50),
	startdate timestamp,
	enddate timestamp,
	interesrate double precision,
	accounttyp varchar(255),
	accounttypeid integer,
	constraint sys_pk_10147 primary key (accountid),
	constraint fk_account_office foreign key (officeid) references branches(officeid)
);

-- public.public.customers definition

-- drop table

 drop table if exists customers;

create table customers (
	customerid integer not null generated by default as identity (start with 1),
	customertypeid integer,
	name varchar(255),
	address varchar(255),
	comments varchar(10485760),
	startdate timestamp,
	photo bytea,
	surname varchar(255),
	id varchar(50),
	email varchar(255),
	comments_en_us varchar(10485760),
	comments_es_es varchar(10485760),
	comments_gl_es varchar(10485760),
	longitude double precision,
	latitude double precision,
	signature bytea,
	id_dms_doc integer,
	country varchar(50),
	state varchar(50),
	zipcode varchar(50),
	phone varchar(50),
	constraint sys_pk_10131 primary key (customerid),
	constraint fk_customertype_customer foreign key (customertypeid) references customertypes(customertypeid),
	constraint fk_tdms_doc_customer foreign key (id_dms_doc) references tdms_doc(id_dms_doc)
);

-- public.public.employees definition

-- drop table

 drop table if exists employees;

create table employees (
	employeeid integer not null generated by default as identity (start with 1),
	employeetypeid integer,
	employeename varchar(255),
	employeesurname varchar(255),
	employeeaddress varchar(255),
	employeecomments varchar(10485760),
	employeestartdate timestamp,
	employeephoto bytea,
	employeeemail varchar(255),
	officeid varchar(50),
	employeephone varchar(50),
	longitude double precision,
	latitude double precision,
	agendaxml varchar(10485760),
	constraint sys_pk_10186 primary key (employeeid),
	constraint fk_employ_brach foreign key (officeid) references branches(officeid),
	constraint fk_employ_type foreign key (employeetypeid) references employeetypes(employeetypeid)
);

-- public.public.movements definition

-- drop table

 drop table if exists movements;

create table movements (
	movementid integer not null generated by default as identity (start with 1),
	accountid integer not null,
	date_ timestamp,
	concept varchar(50),
	movementtypeid integer,
	movement double precision,
	comments varchar(10485760),
	concept_en_us varchar(50),
	concept_es_es varchar(50),
	concept_gl_es varchar(50),
	comments_en_us varchar(10485760),
	comments_es_es varchar(10485760),
	comments_gl_es varchar(10485760),
	constraint sys_pk_10170 primary key (movementid),
	constraint fk_movement_account foreign key (accountid) references accounts(accountid),
	constraint fk_movement_type foreign key (movementtypeid) references movementtypes(movementtypeid)
);

-- public.public.report_parameters definition

-- drop table

 drop table if exists report_parameters;

create table report_parameters (
	id integer not null generated by default as identity (start with 1),
	name varchar(255),
	description varchar(255),
	nested_type varchar(255),
	value_class varchar(255),
	report_id integer,
	constraint sys_pk_10477 primary key (id),
	constraint sys_fk_10478 foreign key (report_id) references reports(id)
);

-- public.public.tdms_doc_category definition

-- drop table

 drop table if exists tdms_doc_category;

create table tdms_doc_category (
	id_dms_doc_category integer not null generated by default as identity (start with 1),
	id_dms_doc integer not null,
	id_dms_doc_category_parent integer,
	category_name varchar(255) not null,
	constraint tdms_doc_category__pk primary key (id_dms_doc_category),
	constraint tdms_doc_category_tdms_doc_fk foreign key (id_dms_doc) references tdms_doc(id_dms_doc)
);

-- public.public.tdms_doc_file definition

-- drop table

 drop table if exists tdms_doc_file;

create table tdms_doc_file (
	id_dms_doc_file integer not null generated by default as identity (start with 1),
	file_name varchar(255) not null,
	id_dms_doc integer not null,
	file_type varchar(255),
	id_dms_doc_category integer,
	constraint tdms_doc_file_pk primary key (id_dms_doc_file),
	constraint tdms_doc_file_tdms_doc_category_fk foreign key (id_dms_doc_category) references tdms_doc_category(id_dms_doc_category),
	constraint tdms_doc_file_tdms_doc_fk foreign key (id_dms_doc) references tdms_doc(id_dms_doc)
);

-- public.public.tdms_doc_file_version definition

-- drop table

 drop table if exists tdms_doc_file_version;

create table tdms_doc_file_version (
	id_dms_doc_file_version integer not null generated by default as identity (start with 1),
	file_path varchar(500),
	version integer not null,
	file_description text,
	is_active character(1) not null,
	file_added_date timestamp not null,
	file_added_user_id integer not null,
	id_dms_doc_file integer not null,
	thumbnail bytea,
	file_size integer,
	constraint tdms_doc_file_version_pk primary key (id_dms_doc_file_version),
	constraint tdms_doc_file_version_tdms_doc__file_fk foreign key (id_dms_doc_file) references tdms_doc_file(id_dms_doc_file)
);

-- public.public.tdms_doc_property definition

-- drop table

 drop table if exists tdms_doc_property;

create table tdms_doc_property (
	id_dms_doc_property integer not null generated by default as identity (start with 1),
	doc_property_key varchar(255) not null,
	doc_property_value varchar(255),
	id_dms_doc integer not null,
	constraint tdms_doc_property_pk primary key (id_dms_doc_property),
	constraint tdms_doc_property_tdms_doc foreign key (id_dms_doc) references tdms_doc(id_dms_doc)
);

-- public.public.tdms_related_doc definition

-- drop table

 drop table if exists tdms_related_doc;

create table tdms_related_doc (
	id_dms_related_doc integer not null generated by default as identity (start with 1),
	id_dms_doc_master integer not null,
	id_dms_doc_child integer not null,
	constraint tdms_related_doc_pk primary key (id_dms_related_doc),
	constraint tdms_related_doc_tdms_doc_child_fk foreign key (id_dms_doc_child) references tdms_doc(id_dms_doc),
	constraint tdms_related_doc_tdms_doc_master_fk foreign key (id_dms_doc_master) references tdms_doc(id_dms_doc)
);

-- public.public.trole_server_permission definition

-- drop table

 drop table if exists trole_server_permission;

create table trole_server_permission (
	id_role_server_permission integer not null generated by default as identity (start with 1),
	id_rolename integer,
	id_server_permission integer,
	constraint sys_pk_10112 primary key (id_role_server_permission),
	constraint fk_trole_server_permission foreign key (id_rolename) references trole(id_rolename),
	constraint fk_tserver_permission foreign key (id_server_permission) references tserver_permission(id_server_permission)
);

-- public.public.tuser_role definition

-- drop table

 drop table if exists tuser_role;

create table tuser_role (
	id_user_role integer not null generated by default as identity (start with 1),
	id_rolename integer,
	user_ varchar(50),
	constraint sys_pk_10100 primary key (id_user_role),
	constraint fk_trole foreign key (id_rolename) references trole(id_rolename),
	constraint fk_tuser foreign key (user_) references tuser(user_)
);

-- public.public.webclient_config definition

-- drop table

 drop table if exists webclient_config;

create table webclient_config (
	user_ varchar(50) not null,
	app_uuid varchar(100) not null,
	configuration varchar(10485760),
	constraint webclient_config_pk primary key (user_,app_uuid),
	constraint webclient_config_tuser_fk foreign key (user_) references tuser(user_)
);

-- public.public.customeraccounts definition

-- drop table

 drop table if exists customeraccounts;

create table customeraccounts (
	customeraccountid integer not null generated by default as identity (start with 1),
	customerid integer not null,
	accountid integer not null,
	isowner boolean,
	constraint sys_pk_10157 primary key (customeraccountid),
	constraint fk_account foreign key (accountid) references accounts(accountid),
	constraint fk_customer foreign key (customerid) references customers(customerid)
);
