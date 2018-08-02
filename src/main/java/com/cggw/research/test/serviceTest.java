package com.cggw.research.test;

import com.cggw.article.domain.Article;
import com.cggw.article.service.ArticleService;
import com.cggw.register.domain.Doctor;
import com.cggw.register.domain.Hospital;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cgw on 2018/7/29.
 */
public class serviceTest {
    private TransportClient client;

    private String hospIndex = "hosp_index";
    private String hospType = "hosp_type";
    private String[] hospIndics ={"hosp_index"};

    private String docIndex = "doc_index";
    private String docType = "doc_type";
    private String[] docIndics ={"hospital_index"};

    public void setup() throws UnknownHostException {
        //设置集群   sniff 自动探查功能（每5秒会自动查询是否有node连接至es集群）
        Settings settings = Settings.builder().
                put("cluster.name","elasticsearch").put("client.transport.sniff",true).build();
        //创建Client,将Node连接至es集群中
        client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"),9300));
    }


    public void cleanUp(){
        client.close();
    }


    //添加医生
    @Test
    public void testAddDoc() throws ParseException, IOException {
        Doctor doctor = new Doctor(1101, "靠你吉瓦", "男", "18262638635", "第三人民医院","A","妇产科","001",10.00,"123","good" );
        addDoc(doctor);
    }
    // public Hospital(Integer hId, String hName, String hType, String hAddr, String hPic, Double hScore, String hAccess) {

    //添加医院
    @Test
    public void testAddHosp() throws ParseException, IOException {
        Hospital hospital = new Hospital(0002, "第三人民医院", "A", "绿警大道1号", "123456", 10.00, "123456");
        addHosp(hospital);
    }

    public boolean addHosp(Hospital hospital) throws IOException {
        setup();
        XContentBuilder source = XContentFactory.jsonBuilder();
        source.startObject()
                .field("h_id",hospital.getHId())
                .field("h_name",hospital.getHName())
                .field("h_type",hospital.getHType())
                .field("h_access",hospital.getHAccess())
                .field("h_addr",hospital.getHAddr())
                .field("h_pic",hospital.getHPic())
                .field("h_score",hospital.getHScore())
                .endObject();
        IndexResponse response = client.prepareIndex(hospIndex,hospType,hospital.getHId().toString()).setSource(source).get();
        cleanUp();
        if(response!=null){
            return true;
        }
        return false;
    }

    public boolean addDoc(Doctor doctor) throws IOException {
        setup();
        XContentBuilder source = XContentFactory.jsonBuilder();
        source.startObject()
                .field("d_id",doctor.getDId())
                .field("d_name",doctor.getDName())
                .field("d_sex",doctor.getDSex())
                .field("d_tel",doctor.getDTel())
                .field("d_introduction",doctor.getDIntroduction())
                .field("h_id",doctor.getHId())
                .field("h_type",doctor.getHType())
                .field("h_dept",doctor.getHDept())
                .field("h_room",doctor.getHRoom())
                .field("d_score",doctor.getDScore())
                .field("d_pic",doctor.getDPic())
                .endObject();
        IndexResponse response = client.prepareIndex(docIndex,docType,doctor.getDId().toString()).setSource(source).get();
        cleanUp();
        if(response!=null){
            return true;
        }
        return false;
    }
}
