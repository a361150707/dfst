package com.ennuova.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 实时数据
 */
@Entity
@Table(name = "CN_SSCLXX")
public class CnSsclxx implements java.io.Serializable {

	// Fields

	private Long id;
	private PubCarinfo pubCarinfo;
	private String f1;
	private String f2;
	private String f3;
	private String f4;
	private String f5;
	private String f6;
	private String f7;
	private String f8;
	private String f9;
	private String f10;
	private String f11;
	private String f12;
	private String f13;
	private String f14;
	private String f15;
	private String f16;
	private String f17;
	private String f18;
	private String f19;
	private String f20;
	private String f21;
	private String f22;
	private String f23;
	private String f24;
	private String f25;
	private String f26;
	private String f27;
	private String f28;
	private String f29;
	private String f30;
	private String f31;
	private String f32;
	private String f33;
	private String f34;
	private String f35;
	private String f36;
	private String f37;
	private String f38;
	private String f39;
	private String f40;
	private String f41;
	private String f42;
	private String f43;
	private String f44;
	private String f45;
	private String f46;
	private String f47;
	private String f48;
	private String f49;
	private String f50;
	private String f51;
	private String f52;
	private String f53;
	private String f54;
	private String f55;
	private String f56;
	private String f57;
	private String f58;
	private String f59;
	private String f60;
	private String f61;
	private String f62;
	private String f63;
	private String f64;
	private String f65;
	private String f66;
	private String f67;
	private String f68;
	private String f69;
	private String f70;
	private String f71;
	private String f72;
	private String f73;
	private String f74;
	private String f75;
	private String f76;
	private String f77;
	private String f78;
	private String f79;
	private String f80;
	private String f81;
	private String f82;
	private String f83;
	private String f84;
	private String f85;
	private String f86;
	private String f87;
	private String f88;
	private String f89;
	private String f90;
	private String f91;
	private String f92;
	private String f93;
	private String f94;
	private String f95;
	private String f96;
	private String f97;
	private String f98;
	private String f99;
	private String f100;
	private String f101;
	private String f102;
	private String f103;
	private String f104;
	private String f105;
	private String f106;
	private String f107;
	private String f108;
	private String f109;
	private String f110;
	private String f111;
	private String f112;
	private String f113;
	private String f114;
	private String f115;
	private String f116;
	private String f117;
	private String f118;
	private String f119;
	private String f120;
	private String f121;
	private String f122;
	private String f123;
	private String f124;
	private String f125;
	private Timestamp fcreatetime;

	// Constructors

	/** default constructor */
	public CnSsclxx() {
	}

	/** minimal constructor */
	public CnSsclxx(Long id) {
		this.id = id;
	}

	/** full constructor */
	public CnSsclxx(Long id, PubCarinfo pubCarinfo, String f1, String f2,
			String f3, String f4, String f5, String f6, String f7, String f8,
			String f9, String f10, String f11, String f12, String f13,
			String f14, String f15, String f16, String f17, String f18,
			String f19, String f20, String f21, String f22, String f23,
			String f24, String f25, String f26, String f27, String f28,
			String f29, String f30, String f31, String f32, String f33,
			String f34, String f35, String f36, String f37, String f38,
			String f39, String f40, String f41, String f42, String f43,
			String f44, String f45, String f46, String f47, String f48,
			String f49, String f50, String f51, String f52, String f53,
			String f54, String f55, String f56, String f57, String f58,
			String f59, String f60, String f61, String f62, String f63,
			String f64, String f65, String f66, String f67, String f68,
			String f69, String f70, String f71, String f72, String f73,
			String f74, String f75, String f76, String f77, String f78,
			String f79, String f80, String f81, String f82, String f83,
			String f84, String f85, String f86, String f87, String f88,
			String f89, String f90, String f91, String f92, String f93,
			String f94, String f95, String f96, String f97, String f98,
			String f99, String f100, String f101, String f102, String f103,
			String f104, String f105, String f106, String f107, String f108,
			String f109, String f110, String f111, String f112, String f113,
			String f114, String f115, String f116, String f117, String f118,
			String f119, String f120, String f121, String f122, String f123,
			String f124, String f125, Timestamp fcreatetime) {
		this.id = id;
		this.pubCarinfo = pubCarinfo;
		this.f1 = f1;
		this.f2 = f2;
		this.f3 = f3;
		this.f4 = f4;
		this.f5 = f5;
		this.f6 = f6;
		this.f7 = f7;
		this.f8 = f8;
		this.f9 = f9;
		this.f10 = f10;
		this.f11 = f11;
		this.f12 = f12;
		this.f13 = f13;
		this.f14 = f14;
		this.f15 = f15;
		this.f16 = f16;
		this.f17 = f17;
		this.f18 = f18;
		this.f19 = f19;
		this.f20 = f20;
		this.f21 = f21;
		this.f22 = f22;
		this.f23 = f23;
		this.f24 = f24;
		this.f25 = f25;
		this.f26 = f26;
		this.f27 = f27;
		this.f28 = f28;
		this.f29 = f29;
		this.f30 = f30;
		this.f31 = f31;
		this.f32 = f32;
		this.f33 = f33;
		this.f34 = f34;
		this.f35 = f35;
		this.f36 = f36;
		this.f37 = f37;
		this.f38 = f38;
		this.f39 = f39;
		this.f40 = f40;
		this.f41 = f41;
		this.f42 = f42;
		this.f43 = f43;
		this.f44 = f44;
		this.f45 = f45;
		this.f46 = f46;
		this.f47 = f47;
		this.f48 = f48;
		this.f49 = f49;
		this.f50 = f50;
		this.f51 = f51;
		this.f52 = f52;
		this.f53 = f53;
		this.f54 = f54;
		this.f55 = f55;
		this.f56 = f56;
		this.f57 = f57;
		this.f58 = f58;
		this.f59 = f59;
		this.f60 = f60;
		this.f61 = f61;
		this.f62 = f62;
		this.f63 = f63;
		this.f64 = f64;
		this.f65 = f65;
		this.f66 = f66;
		this.f67 = f67;
		this.f68 = f68;
		this.f69 = f69;
		this.f70 = f70;
		this.f71 = f71;
		this.f72 = f72;
		this.f73 = f73;
		this.f74 = f74;
		this.f75 = f75;
		this.f76 = f76;
		this.f77 = f77;
		this.f78 = f78;
		this.f79 = f79;
		this.f80 = f80;
		this.f81 = f81;
		this.f82 = f82;
		this.f83 = f83;
		this.f84 = f84;
		this.f85 = f85;
		this.f86 = f86;
		this.f87 = f87;
		this.f88 = f88;
		this.f89 = f89;
		this.f90 = f90;
		this.f91 = f91;
		this.f92 = f92;
		this.f93 = f93;
		this.f94 = f94;
		this.f95 = f95;
		this.f96 = f96;
		this.f97 = f97;
		this.f98 = f98;
		this.f99 = f99;
		this.f100 = f100;
		this.f101 = f101;
		this.f102 = f102;
		this.f103 = f103;
		this.f104 = f104;
		this.f105 = f105;
		this.f106 = f106;
		this.f107 = f107;
		this.f108 = f108;
		this.f109 = f109;
		this.f110 = f110;
		this.f111 = f111;
		this.f112 = f112;
		this.f113 = f113;
		this.f114 = f114;
		this.f115 = f115;
		this.f116 = f116;
		this.f117 = f117;
		this.f118 = f118;
		this.f119 = f119;
		this.f120 = f120;
		this.f121 = f121;
		this.f122 = f122;
		this.f123 = f123;
		this.f124 = f124;
		this.f125 = f125;
		this.fcreatetime = fcreatetime;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_CN_SSCLXX",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FCLID")
	public PubCarinfo getPubCarinfo() {
		return this.pubCarinfo;
	}

	public void setPubCarinfo(PubCarinfo pubCarinfo) {
		this.pubCarinfo = pubCarinfo;
	}

	@Column(name = "F1", length = 50)
	public String getF1() {
		return this.f1;
	}

	public void setF1(String f1) {
		this.f1 = f1;
	}

	@Column(name = "F2", length = 50)
	public String getF2() {
		return this.f2;
	}

	public void setF2(String f2) {
		this.f2 = f2;
	}

	@Column(name = "F3", length = 50)
	public String getF3() {
		return this.f3;
	}

	public void setF3(String f3) {
		this.f3 = f3;
	}

	@Column(name = "F4", length = 50)
	public String getF4() {
		return this.f4;
	}

	public void setF4(String f4) {
		this.f4 = f4;
	}

	@Column(name = "F5", length = 50)
	public String getF5() {
		return this.f5;
	}

	public void setF5(String f5) {
		this.f5 = f5;
	}

	@Column(name = "F6", length = 50)
	public String getF6() {
		return this.f6;
	}

	public void setF6(String f6) {
		this.f6 = f6;
	}

	@Column(name = "F7", length = 50)
	public String getF7() {
		return this.f7;
	}

	public void setF7(String f7) {
		this.f7 = f7;
	}

	@Column(name = "F8", length = 50)
	public String getF8() {
		return this.f8;
	}

	public void setF8(String f8) {
		this.f8 = f8;
	}

	@Column(name = "F9", length = 50)
	public String getF9() {
		return this.f9;
	}

	public void setF9(String f9) {
		this.f9 = f9;
	}

	@Column(name = "F10", length = 50)
	public String getF10() {
		return this.f10;
	}

	public void setF10(String f10) {
		this.f10 = f10;
	}

	@Column(name = "F11", length = 50)
	public String getF11() {
		return this.f11;
	}

	public void setF11(String f11) {
		this.f11 = f11;
	}

	@Column(name = "F12", length = 50)
	public String getF12() {
		return this.f12;
	}

	public void setF12(String f12) {
		this.f12 = f12;
	}

	@Column(name = "F13", length = 50)
	public String getF13() {
		return this.f13;
	}

	public void setF13(String f13) {
		this.f13 = f13;
	}

	@Column(name = "F14", length = 50)
	public String getF14() {
		return this.f14;
	}

	public void setF14(String f14) {
		this.f14 = f14;
	}

	@Column(name = "F15", length = 50)
	public String getF15() {
		return this.f15;
	}

	public void setF15(String f15) {
		this.f15 = f15;
	}

	@Column(name = "F16", length = 50)
	public String getF16() {
		return this.f16;
	}

	public void setF16(String f16) {
		this.f16 = f16;
	}

	@Column(name = "F17", length = 50)
	public String getF17() {
		return this.f17;
	}

	public void setF17(String f17) {
		this.f17 = f17;
	}

	@Column(name = "F18", length = 50)
	public String getF18() {
		return this.f18;
	}

	public void setF18(String f18) {
		this.f18 = f18;
	}

	@Column(name = "F19", length = 50)
	public String getF19() {
		return this.f19;
	}

	public void setF19(String f19) {
		this.f19 = f19;
	}

	@Column(name = "F20", length = 50)
	public String getF20() {
		return this.f20;
	}

	public void setF20(String f20) {
		this.f20 = f20;
	}

	@Column(name = "F21", length = 50)
	public String getF21() {
		return this.f21;
	}

	public void setF21(String f21) {
		this.f21 = f21;
	}

	@Column(name = "F22", length = 50)
	public String getF22() {
		return this.f22;
	}

	public void setF22(String f22) {
		this.f22 = f22;
	}

	@Column(name = "F23", length = 50)
	public String getF23() {
		return this.f23;
	}

	public void setF23(String f23) {
		this.f23 = f23;
	}

	@Column(name = "F24", length = 50)
	public String getF24() {
		return this.f24;
	}

	public void setF24(String f24) {
		this.f24 = f24;
	}

	@Column(name = "F25", length = 50)
	public String getF25() {
		return this.f25;
	}

	public void setF25(String f25) {
		this.f25 = f25;
	}

	@Column(name = "F26", length = 50)
	public String getF26() {
		return this.f26;
	}

	public void setF26(String f26) {
		this.f26 = f26;
	}

	@Column(name = "F27", length = 50)
	public String getF27() {
		return this.f27;
	}

	public void setF27(String f27) {
		this.f27 = f27;
	}

	@Column(name = "F28", length = 50)
	public String getF28() {
		return this.f28;
	}

	public void setF28(String f28) {
		this.f28 = f28;
	}

	@Column(name = "F29", length = 50)
	public String getF29() {
		return this.f29;
	}

	public void setF29(String f29) {
		this.f29 = f29;
	}

	@Column(name = "F30", length = 50)
	public String getF30() {
		return this.f30;
	}

	public void setF30(String f30) {
		this.f30 = f30;
	}

	@Column(name = "F31", length = 50)
	public String getF31() {
		return this.f31;
	}

	public void setF31(String f31) {
		this.f31 = f31;
	}

	@Column(name = "F32", length = 50)
	public String getF32() {
		return this.f32;
	}

	public void setF32(String f32) {
		this.f32 = f32;
	}

	@Column(name = "F33", length = 50)
	public String getF33() {
		return this.f33;
	}

	public void setF33(String f33) {
		this.f33 = f33;
	}

	@Column(name = "F34", length = 50)
	public String getF34() {
		return this.f34;
	}

	public void setF34(String f34) {
		this.f34 = f34;
	}

	@Column(name = "F35", length = 50)
	public String getF35() {
		return this.f35;
	}

	public void setF35(String f35) {
		this.f35 = f35;
	}

	@Column(name = "F36", length = 50)
	public String getF36() {
		return this.f36;
	}

	public void setF36(String f36) {
		this.f36 = f36;
	}

	@Column(name = "F37", length = 50)
	public String getF37() {
		return this.f37;
	}

	public void setF37(String f37) {
		this.f37 = f37;
	}

	@Column(name = "F38", length = 50)
	public String getF38() {
		return this.f38;
	}

	public void setF38(String f38) {
		this.f38 = f38;
	}

	@Column(name = "F39", length = 50)
	public String getF39() {
		return this.f39;
	}

	public void setF39(String f39) {
		this.f39 = f39;
	}

	@Column(name = "F40", length = 50)
	public String getF40() {
		return this.f40;
	}

	public void setF40(String f40) {
		this.f40 = f40;
	}

	@Column(name = "F41", length = 50)
	public String getF41() {
		return this.f41;
	}

	public void setF41(String f41) {
		this.f41 = f41;
	}

	@Column(name = "F42", length = 50)
	public String getF42() {
		return this.f42;
	}

	public void setF42(String f42) {
		this.f42 = f42;
	}

	@Column(name = "F43", length = 50)
	public String getF43() {
		return this.f43;
	}

	public void setF43(String f43) {
		this.f43 = f43;
	}

	@Column(name = "F44", length = 50)
	public String getF44() {
		return this.f44;
	}

	public void setF44(String f44) {
		this.f44 = f44;
	}

	@Column(name = "F45", length = 50)
	public String getF45() {
		return this.f45;
	}

	public void setF45(String f45) {
		this.f45 = f45;
	}

	@Column(name = "F46", length = 50)
	public String getF46() {
		return this.f46;
	}

	public void setF46(String f46) {
		this.f46 = f46;
	}

	@Column(name = "F47", length = 50)
	public String getF47() {
		return this.f47;
	}

	public void setF47(String f47) {
		this.f47 = f47;
	}

	@Column(name = "F48", length = 50)
	public String getF48() {
		return this.f48;
	}

	public void setF48(String f48) {
		this.f48 = f48;
	}

	@Column(name = "F49", length = 50)
	public String getF49() {
		return this.f49;
	}

	public void setF49(String f49) {
		this.f49 = f49;
	}

	@Column(name = "F50", length = 50)
	public String getF50() {
		return this.f50;
	}

	public void setF50(String f50) {
		this.f50 = f50;
	}

	@Column(name = "F51", length = 50)
	public String getF51() {
		return this.f51;
	}

	public void setF51(String f51) {
		this.f51 = f51;
	}

	@Column(name = "F52", length = 50)
	public String getF52() {
		return this.f52;
	}

	public void setF52(String f52) {
		this.f52 = f52;
	}

	@Column(name = "F53", length = 50)
	public String getF53() {
		return this.f53;
	}

	public void setF53(String f53) {
		this.f53 = f53;
	}

	@Column(name = "F54", length = 50)
	public String getF54() {
		return this.f54;
	}

	public void setF54(String f54) {
		this.f54 = f54;
	}

	@Column(name = "F55", length = 50)
	public String getF55() {
		return this.f55;
	}

	public void setF55(String f55) {
		this.f55 = f55;
	}

	@Column(name = "F56", length = 50)
	public String getF56() {
		return this.f56;
	}

	public void setF56(String f56) {
		this.f56 = f56;
	}

	@Column(name = "F57", length = 50)
	public String getF57() {
		return this.f57;
	}

	public void setF57(String f57) {
		this.f57 = f57;
	}

	@Column(name = "F58", length = 50)
	public String getF58() {
		return this.f58;
	}

	public void setF58(String f58) {
		this.f58 = f58;
	}

	@Column(name = "F59", length = 50)
	public String getF59() {
		return this.f59;
	}

	public void setF59(String f59) {
		this.f59 = f59;
	}

	@Column(name = "F60", length = 50)
	public String getF60() {
		return this.f60;
	}

	public void setF60(String f60) {
		this.f60 = f60;
	}

	@Column(name = "F61", length = 50)
	public String getF61() {
		return this.f61;
	}

	public void setF61(String f61) {
		this.f61 = f61;
	}

	@Column(name = "F62", length = 50)
	public String getF62() {
		return this.f62;
	}

	public void setF62(String f62) {
		this.f62 = f62;
	}

	@Column(name = "F63", length = 50)
	public String getF63() {
		return this.f63;
	}

	public void setF63(String f63) {
		this.f63 = f63;
	}

	@Column(name = "F64", length = 50)
	public String getF64() {
		return this.f64;
	}

	public void setF64(String f64) {
		this.f64 = f64;
	}

	@Column(name = "F65", length = 50)
	public String getF65() {
		return this.f65;
	}

	public void setF65(String f65) {
		this.f65 = f65;
	}

	@Column(name = "F66", length = 50)
	public String getF66() {
		return this.f66;
	}

	public void setF66(String f66) {
		this.f66 = f66;
	}

	@Column(name = "F67", length = 50)
	public String getF67() {
		return this.f67;
	}

	public void setF67(String f67) {
		this.f67 = f67;
	}

	@Column(name = "F68", length = 50)
	public String getF68() {
		return this.f68;
	}

	public void setF68(String f68) {
		this.f68 = f68;
	}

	@Column(name = "F69", length = 50)
	public String getF69() {
		return this.f69;
	}

	public void setF69(String f69) {
		this.f69 = f69;
	}

	@Column(name = "F70", length = 50)
	public String getF70() {
		return this.f70;
	}

	public void setF70(String f70) {
		this.f70 = f70;
	}

	@Column(name = "F71", length = 50)
	public String getF71() {
		return this.f71;
	}

	public void setF71(String f71) {
		this.f71 = f71;
	}

	@Column(name = "F72", length = 50)
	public String getF72() {
		return this.f72;
	}

	public void setF72(String f72) {
		this.f72 = f72;
	}

	@Column(name = "F73", length = 50)
	public String getF73() {
		return this.f73;
	}

	public void setF73(String f73) {
		this.f73 = f73;
	}

	@Column(name = "F74", length = 50)
	public String getF74() {
		return this.f74;
	}

	public void setF74(String f74) {
		this.f74 = f74;
	}

	@Column(name = "F75", length = 50)
	public String getF75() {
		return this.f75;
	}

	public void setF75(String f75) {
		this.f75 = f75;
	}

	@Column(name = "F76", length = 50)
	public String getF76() {
		return this.f76;
	}

	public void setF76(String f76) {
		this.f76 = f76;
	}

	@Column(name = "F77", length = 50)
	public String getF77() {
		return this.f77;
	}

	public void setF77(String f77) {
		this.f77 = f77;
	}

	@Column(name = "F78", length = 50)
	public String getF78() {
		return this.f78;
	}

	public void setF78(String f78) {
		this.f78 = f78;
	}

	@Column(name = "F79", length = 50)
	public String getF79() {
		return this.f79;
	}

	public void setF79(String f79) {
		this.f79 = f79;
	}

	@Column(name = "F80", length = 50)
	public String getF80() {
		return this.f80;
	}

	public void setF80(String f80) {
		this.f80 = f80;
	}

	@Column(name = "F81", length = 50)
	public String getF81() {
		return this.f81;
	}

	public void setF81(String f81) {
		this.f81 = f81;
	}

	@Column(name = "F82", length = 50)
	public String getF82() {
		return this.f82;
	}

	public void setF82(String f82) {
		this.f82 = f82;
	}

	@Column(name = "F83", length = 50)
	public String getF83() {
		return this.f83;
	}

	public void setF83(String f83) {
		this.f83 = f83;
	}

	@Column(name = "F84", length = 50)
	public String getF84() {
		return this.f84;
	}

	public void setF84(String f84) {
		this.f84 = f84;
	}

	@Column(name = "F85", length = 50)
	public String getF85() {
		return this.f85;
	}

	public void setF85(String f85) {
		this.f85 = f85;
	}

	@Column(name = "F86", length = 50)
	public String getF86() {
		return this.f86;
	}

	public void setF86(String f86) {
		this.f86 = f86;
	}

	@Column(name = "F87", length = 50)
	public String getF87() {
		return this.f87;
	}

	public void setF87(String f87) {
		this.f87 = f87;
	}

	@Column(name = "F88", length = 50)
	public String getF88() {
		return this.f88;
	}

	public void setF88(String f88) {
		this.f88 = f88;
	}

	@Column(name = "F89", length = 50)
	public String getF89() {
		return this.f89;
	}

	public void setF89(String f89) {
		this.f89 = f89;
	}

	@Column(name = "F90", length = 50)
	public String getF90() {
		return this.f90;
	}

	public void setF90(String f90) {
		this.f90 = f90;
	}

	@Column(name = "F91", length = 50)
	public String getF91() {
		return this.f91;
	}

	public void setF91(String f91) {
		this.f91 = f91;
	}

	@Column(name = "F92", length = 50)
	public String getF92() {
		return this.f92;
	}

	public void setF92(String f92) {
		this.f92 = f92;
	}

	@Column(name = "F93", length = 50)
	public String getF93() {
		return this.f93;
	}

	public void setF93(String f93) {
		this.f93 = f93;
	}

	@Column(name = "F94", length = 50)
	public String getF94() {
		return this.f94;
	}

	public void setF94(String f94) {
		this.f94 = f94;
	}

	@Column(name = "F95", length = 50)
	public String getF95() {
		return this.f95;
	}

	public void setF95(String f95) {
		this.f95 = f95;
	}

	@Column(name = "F96", length = 50)
	public String getF96() {
		return this.f96;
	}

	public void setF96(String f96) {
		this.f96 = f96;
	}

	@Column(name = "F97", length = 50)
	public String getF97() {
		return this.f97;
	}

	public void setF97(String f97) {
		this.f97 = f97;
	}

	@Column(name = "F98", length = 50)
	public String getF98() {
		return this.f98;
	}

	public void setF98(String f98) {
		this.f98 = f98;
	}

	@Column(name = "F99", length = 50)
	public String getF99() {
		return this.f99;
	}

	public void setF99(String f99) {
		this.f99 = f99;
	}

	@Column(name = "F100", length = 50)
	public String getF100() {
		return this.f100;
	}

	public void setF100(String f100) {
		this.f100 = f100;
	}

	@Column(name = "F101", length = 50)
	public String getF101() {
		return this.f101;
	}

	public void setF101(String f101) {
		this.f101 = f101;
	}

	@Column(name = "F102", length = 50)
	public String getF102() {
		return this.f102;
	}

	public void setF102(String f102) {
		this.f102 = f102;
	}

	@Column(name = "F103", length = 50)
	public String getF103() {
		return this.f103;
	}

	public void setF103(String f103) {
		this.f103 = f103;
	}

	@Column(name = "F104", length = 50)
	public String getF104() {
		return this.f104;
	}

	public void setF104(String f104) {
		this.f104 = f104;
	}

	@Column(name = "F105", length = 50)
	public String getF105() {
		return this.f105;
	}

	public void setF105(String f105) {
		this.f105 = f105;
	}

	@Column(name = "F106", length = 50)
	public String getF106() {
		return this.f106;
	}

	public void setF106(String f106) {
		this.f106 = f106;
	}

	@Column(name = "F107", length = 50)
	public String getF107() {
		return this.f107;
	}

	public void setF107(String f107) {
		this.f107 = f107;
	}

	@Column(name = "F108", length = 50)
	public String getF108() {
		return this.f108;
	}

	public void setF108(String f108) {
		this.f108 = f108;
	}

	@Column(name = "F109", length = 50)
	public String getF109() {
		return this.f109;
	}

	public void setF109(String f109) {
		this.f109 = f109;
	}

	@Column(name = "F110", length = 50)
	public String getF110() {
		return this.f110;
	}

	public void setF110(String f110) {
		this.f110 = f110;
	}

	@Column(name = "F111", length = 50)
	public String getF111() {
		return this.f111;
	}

	public void setF111(String f111) {
		this.f111 = f111;
	}

	@Column(name = "F112", length = 50)
	public String getF112() {
		return this.f112;
	}

	public void setF112(String f112) {
		this.f112 = f112;
	}

	@Column(name = "F113", length = 50)
	public String getF113() {
		return this.f113;
	}

	public void setF113(String f113) {
		this.f113 = f113;
	}

	@Column(name = "F114", length = 50)
	public String getF114() {
		return this.f114;
	}

	public void setF114(String f114) {
		this.f114 = f114;
	}

	@Column(name = "F115", length = 50)
	public String getF115() {
		return this.f115;
	}

	public void setF115(String f115) {
		this.f115 = f115;
	}

	@Column(name = "F116", length = 50)
	public String getF116() {
		return this.f116;
	}

	public void setF116(String f116) {
		this.f116 = f116;
	}

	@Column(name = "F117", length = 50)
	public String getF117() {
		return this.f117;
	}

	public void setF117(String f117) {
		this.f117 = f117;
	}

	@Column(name = "F118", length = 50)
	public String getF118() {
		return this.f118;
	}

	public void setF118(String f118) {
		this.f118 = f118;
	}

	@Column(name = "F119", length = 50)
	public String getF119() {
		return this.f119;
	}

	public void setF119(String f119) {
		this.f119 = f119;
	}

	@Column(name = "F120", length = 50)
	public String getF120() {
		return this.f120;
	}

	public void setF120(String f120) {
		this.f120 = f120;
	}

	@Column(name = "F121", length = 50)
	public String getF121() {
		return this.f121;
	}

	public void setF121(String f121) {
		this.f121 = f121;
	}

	@Column(name = "F122", length = 50)
	public String getF122() {
		return this.f122;
	}

	public void setF122(String f122) {
		this.f122 = f122;
	}

	@Column(name = "F123", length = 50)
	public String getF123() {
		return this.f123;
	}

	public void setF123(String f123) {
		this.f123 = f123;
	}

	@Column(name = "F124", length = 50)
	public String getF124() {
		return this.f124;
	}

	public void setF124(String f124) {
		this.f124 = f124;
	}

	@Column(name = "F125", length = 50)
	public String getF125() {
		return this.f125;
	}

	public void setF125(String f125) {
		this.f125 = f125;
	}

	@Column(name = "FCREATETIME", length = 7)
	public Timestamp getFcreatetime() {
		return this.fcreatetime;
	}

	public void setFcreatetime(Timestamp fcreatetime) {
		this.fcreatetime = fcreatetime;
	}

	@Override
	public String toString() {
		return "CnSsclxx [id=" + id + ", pubCarinfo=" + pubCarinfo.getId() + ", f1="
				+ f1 + ", f2=" + f2 + ", f3=" + f3 + ", f4=" + f4 + ", f5="
				+ f5 + ", f6=" + f6 + ", f7=" + f7 + ", f8=" + f8 + ", f9="
				+ f9 + ", f10=" + f10 + ", f11=" + f11 + ", f12=" + f12
				+ ", f13=" + f13 + ", f14=" + f14 + ", f15=" + f15 + ", f16="
				+ f16 + ", f17=" + f17 + ", f18=" + f18 + ", f19=" + f19
				+ ", f20=" + f20 + ", f21=" + f21 + ", f22=" + f22 + ", f23="
				+ f23 + ", f24=" + f24 + ", f25=" + f25 + ", f26=" + f26
				+ ", f27=" + f27 + ", f28=" + f28 + ", f29=" + f29 + ", f30="
				+ f30 + ", f31=" + f31 + ", f32=" + f32 + ", f33=" + f33
				+ ", f34=" + f34 + ", f35=" + f35 + ", f36=" + f36 + ", f37="
				+ f37 + ", f38=" + f38 + ", f39=" + f39 + ", f40=" + f40
				+ ", f41=" + f41 + ", f42=" + f42 + ", f43=" + f43 + ", f44="
				+ f44 + ", f45=" + f45 + ", f46=" + f46 + ", f47=" + f47
				+ ", f48=" + f48 + ", f49=" + f49 + ", f50=" + f50 + ", f51="
				+ f51 + ", f52=" + f52 + ", f53=" + f53 + ", f54=" + f54
				+ ", f55=" + f55 + ", f56=" + f56 + ", f57=" + f57 + ", f58="
				+ f58 + ", f59=" + f59 + ", f60=" + f60 + ", f61=" + f61
				+ ", f62=" + f62 + ", f63=" + f63 + ", f64=" + f64 + ", f65="
				+ f65 + ", f66=" + f66 + ", f67=" + f67 + ", f68=" + f68
				+ ", f69=" + f69 + ", f70=" + f70 + ", f71=" + f71 + ", f72="
				+ f72 + ", f73=" + f73 + ", f74=" + f74 + ", f75=" + f75
				+ ", f76=" + f76 + ", f77=" + f77 + ", f78=" + f78 + ", f79="
				+ f79 + ", f80=" + f80 + ", f81=" + f81 + ", f82=" + f82
				+ ", f83=" + f83 + ", f84=" + f84 + ", f85=" + f85 + ", f86="
				+ f86 + ", f87=" + f87 + ", f88=" + f88 + ", f89=" + f89
				+ ", f90=" + f90 + ", f91=" + f91 + ", f92=" + f92 + ", f93="
				+ f93 + ", f94=" + f94 + ", f95=" + f95 + ", f96=" + f96
				+ ", f97=" + f97 + ", f98=" + f98 + ", f99=" + f99 + ", f100="
				+ f100 + ", f101=" + f101 + ", f102=" + f102 + ", f103=" + f103
				+ ", f104=" + f104 + ", f105=" + f105 + ", f106=" + f106
				+ ", f107=" + f107 + ", f108=" + f108 + ", f109=" + f109
				+ ", f110=" + f110 + ", f111=" + f111 + ", f112=" + f112
				+ ", f113=" + f113 + ", f114=" + f114 + ", f115=" + f115
				+ ", f116=" + f116 + ", f117=" + f117 + ", f118=" + f118
				+ ", f119=" + f119 + ", f120=" + f120 + ", f121=" + f121
				+ ", f122=" + f122 + ", f123=" + f123 + ", f124=" + f124
				+ ", f125=" + f125 + ", fcreatetime=" + fcreatetime + "]";
	}

	

}