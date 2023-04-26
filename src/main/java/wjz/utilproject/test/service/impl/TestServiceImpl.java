package wjz.utilproject.test.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wjz.utilproject.enums.UniqueTypeEnums;
import wjz.utilproject.ninterface.Unique;
import wjz.utilproject.test.service.TestService;
import wjz.utilproject.test.vo.User;
import wjz.utilproject.util.RedisUtil;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/***
 *
 * Author:WangJiazheng
 * Data:2023/4/21 13:34
 *
 */
@Service
@Slf4j
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class TestServiceImpl implements TestService {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void redisTest() {
        try {
            // 创建RedisUtil实例并连接到Redis服务器
            redisUtil.connect();

            // 设置一个字符串类型的键值对，并设置过期时间为60秒
            String key = "demo-key";
            String value = "demo-value";
            redisUtil.set(key, value, 60);

            // 获取该键的值并打印
            String result = redisUtil.get(key, String.class);
            System.out.println("Value of '" + key + "' is: " + result);
            // 关闭Redis连接
            redisUtil.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    @Unique(entityClass = User.class, fieldNames = {"idCode"})
    public void addUser(User user) {
        log.info("校验完成");
    }

    @Override
    @Unique(entityClass = User.class, fieldNames = {"idCode","phone"} ,uniqueType = UniqueTypeEnums.EDIT   )
    public void editUser(User user) {
        log.info("校验完成");
    }

    @Autowired
    @Qualifier("oracleDataSource")
    private DataSource oracleDataSource;

    @Autowired
    @Qualifier("mysqlDataSource")
    private DataSource mysqlDataSource;

    @Override
    public void copyTableFromDatabaseAToDatabaseB() {
        long pk = 12975031;
        while (pk<35006176){
            long pk1 = pk + 50000;
            if (pk1>35006176){
                pk1 = 35006176;
            }
            this.dataToData1(pk,pk1);
            pk = pk1;
        }

    }

    private void dataToData1(long pk, long pk1) {
        try {
            this.dataToData(pk,pk1);
        }catch (Exception e){
            this.dataToData1(pk,pk1);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void dataToData(long pk, long pk1) {
        System.out.println("当前pk范围"+pk+"~"+pk1);
        // 从数据源A查询数据
        JdbcTemplate jdbcTemplateA = new JdbcTemplate(oracleDataSource);
        Long time = System.currentTimeMillis();
        System.out.println("开始查询时间:::"+ System.currentTimeMillis());
        List<Map<String, Object>> dataList = jdbcTemplateA.queryForList("select * from his_data_mx_jd where  pk >= " + pk +" and pk < " + pk1);
        System.out.println("查询完成时间:::"+ System.currentTimeMillis());
        System.out.println("开始插入时间:::"+ System.currentTimeMillis());
        JdbcTemplate jdbcTemplateB = new JdbcTemplate(mysqlDataSource);
        List<Object[]> batchParams = new ArrayList<>();
        for (Map<String, Object> data : dataList) {
            Object[] params = new Object[]{
                    data.get("PK"), data.get("HISID"), data.get("PATIENT_ID"), data.get("ZYH"),
                    data.get("HOSPITAL_ID"), data.get("DISCHARGE_DEPT_ID"), data.get("DISCHARGE_DEPT_NAME"),
                    data.get("P_CATEGORY"), data.get("USAGE_DATE"), data.get("USAGE_DATE_STR"), data.get("ITEM_ID_HOSP"),
                    data.get("ITEM_NAME_HOSP"), data.get("ITEM_ID"), data.get("ITEM_NAME"), data.get("DRUG_SPEC"),
                    data.get("DOSAGE_FORM"), data.get("PACKAGE_UNIT"), data.get("UNIT_PRICE"), data.get("ITEM_NUM"),
                    data.get("ITEM_COST"), data.get("BMI_PAY_AMOUNT"), data.get("AMOUNT_REFUSAL"), data.get("AMOUNT_REASON"),
                    data.get("DISCHARGE_MEDICATION"), data.get("REFUND_FLAG"), data.get("REFUND_DATE")
            };
            batchParams.add(params);
        }
        jdbcTemplateB.batchUpdate(
                "INSERT INTO his_data_mx (PK, HISID, PATIENT_ID, ZYH, HOSPITAL_ID, DISCHARGE_DEPT_ID, DISCHARGE_DEPT_NAME, P_CATEGORY, USAGE_DATE, USAGE_DATE_STR, ITEM_ID_HOSP, ITEM_NAME_HOSP, ITEM_ID, ITEM_NAME, DRUG_SPEC, DOSAGE_FORM, PACKAGE_UNIT, UNIT_PRICE, ITEM_NUM, ITEM_COST, BMI_PAY_AMOUNT, AMOUNT_REFUSAL, AMOUNT_REASON, DISCHARGE_MEDICATION, REFUND_FLAG, REFUND_DATE)" +
                        " VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                batchParams
        );
        System.out.println("插入完成时间:::"+ System.currentTimeMillis());
        System.out.println("总用时:::::::" + (System.currentTimeMillis()-time)/1000);
    }

}
