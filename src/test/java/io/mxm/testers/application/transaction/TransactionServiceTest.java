package io.mxm.testers.application.transaction;

import io.mxm.testers.MyTestApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(

        classes = MyTestApplication.class)
@AutoConfigureMockMvc
class TransactionServiceTest {

//
//    private boolean isArrayNull(List list) {
//        return !(Objects.isNull(list) && list.isEmpty());
//    }
//    @Autowired
//    TransactionService transactionService;
//
//    @Autowired
//    InstituteService instituteService;
//
//
//    @Test
//    void getTxTypeAndPercent() {
//        // the keys are
//        // PAYA,
//        //INT,
//        //CHQ,
//        //TERMINAL,
//        List<TxTypeAndPercent> txTypeAndPercent = transactionService.getTxTypeAndPercent(1401L, 1459L);
//        for (TxTypeAndPercent typeAndPercent : txTypeAndPercent) {
//            System.out.println(typeAndPercent);
//        }
//    }
//
//    @Test
//    void checkThePackageWithTheValues() {
//// line with inst
//        List<MonthNumberAndAmount> yearAndAmount = transactionService.linearChartByInstitute(1401L, 1459L);
//        if (isArrayNull(yearAndAmount))
//            for (MonthNumberAndAmount monthNumberAndAmount : yearAndAmount) {
//                System.out.println(monthNumberAndAmount);
//            }
//    }
//
//
//    @Test
//    void checkThePackageWithTheValuesWithoutParameter() {
//// line without inst
//        List<MonthNumberAndAmount> yearAndAmount = transactionService.linearChartByInstitute(1401L);
//        if (isArrayNull(yearAndAmount))
//            for (MonthNumberAndAmount monthNumberAndAmount : yearAndAmount) {
//                System.out.println(monthNumberAndAmount.getMonthName());
//                System.out.println(monthNumberAndAmount.getAmount());
//            }
//    }
//    @PersistenceContext
//    EntityManager em;
//
//    @Test
//    void dateCreateTest() throws ParseException {
//        String date_string = "04-DEC-22";
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
//        Date date = formatter.parse(date_string);
//        System.out.println("Date value: "+date);
//    }
//
//
//
//
//    @Test
//    void  testView() throws ParseException {
//
//        String date_string = "03-DEC-20";
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
//        Date date = formatter.parse(date_string);
//        CriteriaBuilder builder = em.getCriteriaBuilder();
//        CriteriaQuery<VwAllTx> query = builder.createQuery(VwAllTx.class);
//        Root<VwAllTx> root = query.from(VwAllTx.class);
//
//        Predicate pAmount =builder.between(root.get("txAmount"), 1L, 100000000000L);
//        Predicate pDate =builder.between(root.get("txDateGreg"),date , date);
//        Predicate PreceiverId= builder.equal(root.get("receiverId"),10820094491L);
//        Predicate PeconomyId= builder.equal(root.get("economyId"),1L);
//        Predicate PinstituteId = builder.equal(root.get("instituteId"),1459L);
//
//        CriteriaQuery<VwAllTx> criteriaQuery = query.where(builder.and(pAmount,pDate,PreceiverId,PinstituteId,PeconomyId));
//        List<VwAllTx> resultList = em.createQuery(criteriaQuery.select(root)).getResultList();
//        for (VwAllTx vwAllTx : resultList) {
//            System.out.println(vwAllTx.getReceiverId());
//        }
//        System.out.println(resultList);
//        System.out.println("0000000000000000000000000000000000000000");
//
//
//    }
//
//    @Test
//    void findPageOfTransactionForNationalCodeTest(){
//
////        RequestSearchForTX forTX = new RequestSearchForTX("1390/01/01", null, 1459L, "1", "15000000000000000000", 0, 10, null, null);
//
////        transactionService.findPageOfTransactionForAllTx(forTX);
//    }
}