//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import ir.smartwareco.tab17.Tab17Application;
//import ir.smartwareco.tab17.application._common.city.CityService;
//import ir.smartwareco.tab17.application.chat.ChatService;
//import ir.smartwareco.tab17.application.decilInquiry.EcoInfoService;
//import ir.smartwareco.tab17.application.decilInquiry.dto.IVRResponseDecile;
//import ir.smartwareco.tab17.application.institute.dtos.GetByNameJson;
//import ir.smartwareco.tab17.application.transaction.TransactionService;
//import ir.smartwareco.tab17.application.util.Utils;
//import ir.smartwareco.tab17.configs.importerbatch.ImportingService;
//import ir.smartwareco.tab17.domain.core.chat.ChatRepository;
//import ir.smartwareco.tab17.domain.core.city.City;
//import ir.smartwareco.tab17.domain.core.city.CityRepository;
//import ir.smartwareco.tab17.domain.core.cityOnDW.CityOnDWRepository;
//import ir.smartwareco.tab17.domain.core.cityOnDW.CityOnDw;
//import ir.smartwareco.tab17.domain.core.conversation.Conversation;
//import ir.smartwareco.tab17.domain.core.conversation.ConversationRepository;
//import ir.smartwareco.tab17.domain.core.institute.Institute;
//import ir.smartwareco.tab17.domain.core.institute.InstituteRepository;
//import ir.smartwareco.tab17.domain.core.province.GetProvinceStoreProcedureService;
//import ir.smartwareco.tab17.domain.core.provinceOnDw.ProvinceOnDw;
//import ir.smartwareco.tab17.domain.core.provinceOnDw.ProvinceOnDRepository;
//import ir.smartwareco.tab17.domain.core.province.Province;
//import ir.smartwareco.tab17.domain.core.province.ProvinceRepository;
//import ir.smartwareco.tab17.domain.core.transaction.FileTX;
//import ir.smartwareco.tab17.domain.core.transaction.FileTXRepository;
//import ir.smartwareco.tab17.domain.core.transaction.TransactionStatus;
//import ir.smartwareco.tab17.domain.core.transaction.chequeTX.Cheque;
//import ir.smartwareco.tab17.domain.core.transaction.chequeTX.ChequeRepository;
//import ir.smartwareco.tab17.domain.core.transaction.payaTX.Paya;
//import ir.smartwareco.tab17.domain.core.transaction.payaTX.PayaRepository;
//import ir.smartwareco.tab17.domain.dto.CityCodeAndName;
//import ir.smartwareco.tab17.domain.dto.IVRResponseToken;
//import ir.smartwareco.tab17.domain.dto.MonthNumberAndAmount;
//import ir.smartwareco.tab17.domain.dto.ProvinceProcedureDTO;
//import ir.smartwareco.tab17.domain.usermanagement.users.User;
//import ir.smartwareco.tab17.domain.usermanagement.users.UserRepository;
//import lombok.val;
//import okhttp3.*;
//import org.hibernate.LazyInitializationException;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.batch.core.JobParametersInvalidException;
//import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
//import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
//import org.springframework.batch.core.repository.JobRestartException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.persistence.EntityManager;
//import javax.persistence.ParameterMode;
//import javax.persistence.PersistenceContext;
//import javax.persistence.StoredProcedureQuery;
//import java.io.IOException;
//import java.lang.reflect.Type;
//import java.math.BigInteger;
//import java.time.LocalDateTime;
//import java.util.*;
//
//import static java.nio.file.Paths.get;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(
//
//        classes = Tab17Application.class)
//@AutoConfigureMockMvc
//class SpringTesting {
//
//    @Autowired
//    ChequeRepository chequeRepository;
//    @Autowired
//    ProvinceOnDRepository provinceOnDRepository;
//    @Autowired
//    ProvinceRepository provinceRepository;
//    @Autowired
//    CityOnDWRepository cityOnDWRepository;
//
//
//    @Autowired
//    ConversationRepository conversationRepository;
//
//    @Autowired
//    ChatRepository chatRepository;
//    @Autowired
//    ChatService chatService;
//
//    @Autowired
//    ImportingService importingService;
//
//
////    @Test
////    void test() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
////        importingService.importDataLoader();
////    }
//
//@Test
//    void givePasswordToNulls() {
//
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String[]  m =
//                {
//                        "0579709779",
//                        "6499912816",
//                        "5419937670",
//                        "1060298155",
//                        "6469449964",
//                        "1199496790",
//                        "1239842554",
//                        "1532697449"
//                };
//
//        for (String s : m) {
//            String encode = bCryptPasswordEncoder.encode(s);
//            User u = userRepository.findByNationalCode(s);
//
//            u.setPassword(encode);
//            User save = userRepository.save(u);
//
//
//        }
//    }
//
//
//
//    @Test
//    void populateProvince() {
//        for (ProvinceOnDw provinceOnDw : provinceOnDRepository.findAll()) {
//            Province province = new Province();
//            province.setTitle(provinceOnDw.getProvinceName());
//            province.setId(
//                    Long.valueOf(provinceOnDw.getProvinceId())
//            );
//            try {
//                provinceRepository.save(province);
//            } catch (Exception e) {
//                System.out.println("error for " + province);
//            }
//        }
//        System.out.println("done");
//    }
//
//    @Autowired
//    CityRepository cityRepository;
//
//    @Test
//    void populateCity() {
//
//
//        for (CityOnDw cityOnDw : cityOnDWRepository.findAll()) {
//
//            String countyName = cityOnDw.getCountyName();
//            String provinceName = cityOnDw.getProvinceName();
//            Province byTitle = provinceRepository.findByTitle(provinceName);
//            City city = new City();
//            city.setTitle(countyName);
//            city.setProvince(byTitle);
//            try {
//                cityRepository.save(city);
//            } catch (Exception e) {
//                System.out.println(" error for " + city);
//            }
//        }
//
//    }
//
//    @Autowired
//    CityService cityService;
//
//    @Test
//    void checkCityAndProvince() {
//        Assertions.assertThrows(
//                LazyInitializationException.class,
//                () -> {
//                    cityService.checkConnectionBetweenCityAndProvinceOrThrow(701L, 1L);
//                }
//        );
//    }
//
//    @Test
//    void checkRel() {
//
////        Province tehran = provinceRepository.findByTitle("قزوین");
////        tehran.getCities().forEach(System.out::println);
////        System.out.println(tehran.getCities().size());
////        System.out.println(tehran);
//
//    }
//
//    @Test
//    void cityProvinceIdRel() {
//        provinceRepository.findById(666L);
//        cityRepository.findById(700L);
//        cityRepository.findAllByProvince_Id(666L).forEach(System.out::println);
//
////        System.out.println(provinceRepository.findById(666L));
////        provinceRepository.findById(666L).get()
////                .getCities().forEach(System.out::println);
//    }
//
//    @Autowired
//    InstituteRepository instituteRepository;
//
//    @Test
//    void enumTesting() {
//        TransactionStatus isValid = TransactionStatus.NOT_QUERIED;
//        String chq_type = "acv";
//        String chqOwnerBank = "a";
//        String chqShabaNumber = "a";
//        String chqChequeNumber = "a";
//        String chqChequeStatus = "a";
//        BigInteger chqTransactionAmount = BigInteger.ZERO;
//        String chqReceiverIdNumber = "a";
//        String chqBeneficiaryIdNumber = "a";
//        String chqDebtTime = "a";
//        String chqTransactionReason = "a";
//        String chqTransactionComment = "a";
//        String trackingNumber = "a1";
//        Institute institute = instituteRepository.getById(373L);
//        Cheque cheque = new Cheque(isValid, chq_type,
//                chqOwnerBank,
//                chqShabaNumber,
//                chqChequeNumber,
//                chqChequeStatus,
//                chqTransactionAmount,
//                chqReceiverIdNumber,
//                chqBeneficiaryIdNumber,
//                chqDebtTime,
//                chqTransactionReason,
//                chqTransactionComment,
//                trackingNumber,
//                institute);
//        chequeRepository.save(cheque);
//        System.out.println("=====================================================================");
//        System.out.println(chequeRepository.findByTrackingNumber("a1"));
//        System.out.println("=====================================================================");
//    }
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Test
//    void fetchUserRoles() {
//        Optional<User> byId = userRepository.findById(1348L);
//        System.out.println(byId);
//        byId.get().getRoles().forEach(System.out::println);
//
//
//    }
//
//    @Test
//    void testInstituteWithUserId() {
//        List<Institute> institutes = userRepository.findByInstituteId(551L);
//        System.out.println(institutes);
//        institutes.forEach(System.out::println);
//
//
//    }
//
//    @Test
//    void setDate() {
//// testing for date default format in java
//        Date d = new Date();
//        System.out.println(d);
//
//        LocalDateTime l = LocalDateTime.now();
//        System.out.println(l);
//
//
//    }
//
//    @Test
//    void deleteTehranFromGilan() {
//        Optional<City> byId = cityRepository.findById(1L);
//        System.out.println(byId.get().getProvince());
//        cityRepository.delete(byId.get());
//
//    }
//
//    @Test
//    void find666() {
//        Optional<City> byId = cityRepository.findById(679L);
//        System.out.println(byId.get().getProvince().getId());
//        //679 city
//        //649 province
//    }
//
//    @Test
//    void testUtil() {
//        System.out.println(Utils.getPersianDateFrom8Digit("1401/09/13"));
//
//    }
//
//    @Autowired
//    FileTXRepository fileTXRepository;
//
//    @Test
//    void excel() {
//
//        List<FileTX> all = fileTXRepository.findAll();
//        all.forEach(fileTX -> {
//            System.out.println(fileTX.getName());
//            System.out.println(fileTX.getType());
//            System.out.println(fileTX.getLineNumber());
//        });
//
//
//    }
//
//    @Test
//    void ex() {
//        List<FileTX> byName = fileTXRepository.findByNameOrderByLineNumber("bdcd2b88-5438-417c-b7e7-0fa0e4c27128.xlsx");
//        byName.forEach(
//                fileTX -> {
//                    System.out.println(fileTX.getType());
//                    System.out.println(fileTX.getPayaId());
//                    System.out.println(fileTX.getLineNumber() + 1);
//                }
//        );
//    }
//
//
//    @Test
//    void getUserConversationsList() {
//
//    }
//
//    @Test
//    void createATwoYearFakeTransactionForGettingSumOfThemInTheChartReport() {
//
//        firstAdd2ValidTransactionsOfEveryTypeInEveryMonthInLast2Years();
//
//
//    }
//
//
//    @Autowired
//    PayaRepository payaRepository;
//
//    private void firstAdd2ValidTransactionsOfEveryTypeInEveryMonthInLast2Years() {
//
//
//        TransactionStatus validity = TransactionStatus.VALID;
//        String chq_type = "صیادی";
//        String ownerBank = "بانک رفاه کارگران";
//        String chequeSheba = "IR060170000000100324200001";
//        String stat = "وصول نشده ";
//        BigInteger bigAmount = new BigInteger("98755987348774");
//        String receiverId = "2740903499";
//        String benfId = "0015656500";
//        String debtTime = "1401/09/27";
//        String reason = "کمک به سالمندان";
//        String comment = "موسسسه مالی و اعتباری محک برای کمک به موسسات سالمند کمک مالی";
//        String Tknumber = "434543lkvknvld-dsjfh";
//        String time = "12:10";
//        List<GetByNameJson> ali = instituteRepository.findInstitutesByName("علی");
//        List<GetByNameJson> mahak = instituteRepository.findInstitutesByName("محک");
//        String destbank = "بانک ملت";
//        Institute insti = new Institute(ali.get(0).getId());
//        Cheque chq = new Cheque(
//                validity,
//                chq_type,
//                ownerBank,
//                chequeSheba,
//                chequeSheba,
//                stat,
//                bigAmount,
//                receiverId,
//                benfId,
//                debtTime,
//                reason,
//                comment,
//                Tknumber,
//                insti
//        );
//
//        val id = mahak.get(0).getId();
//        Paya paya = new Paya(validity, Tknumber, debtTime, time, receiverId, chequeSheba, benfId, chequeSheba, bigAmount, ownerBank, destbank, reason, comment, Tknumber, new Institute(id));
//
//        Date far4002 = Utils.getPersianDateFrom8Digit("1400/01/12");
//        Date far400 = Utils.getPersianDateFrom8Digit("1400/01/12");
//
//        Date ord4002 = Utils.getPersianDateFrom8Digit("1400/02/12");
//        Date ord400 = Utils.getPersianDateFrom8Digit("1400/02/12");
//
//        Date khor4002 = Utils.getPersianDateFrom8Digit("1400/03/12");
//        Date khor400 = Utils.getPersianDateFrom8Digit("1400/03/12");
//
//        Date tir4002 = Utils.getPersianDateFrom8Digit("1400/04/12");
//        Date tir400 = Utils.getPersianDateFrom8Digit("1400/04/12");
//
//        Date mor4002 = Utils.getPersianDateFrom8Digit("1400/05/12");
//        Date mor400 = Utils.getPersianDateFrom8Digit("1400/05/12");
//
//        Date shar4002 = Utils.getPersianDateFrom8Digit("1400/06/12");
//        Date shar400 = Utils.getPersianDateFrom8Digit("1400/06/12");
//
//        Date mhr4002 = Utils.getPersianDateFrom8Digit("1400/07/12");
//        Date mhr400 = Utils.getPersianDateFrom8Digit("1400/07/12");
//
//        Date abn4002 = Utils.getPersianDateFrom8Digit("1400/08/12");
//        Date abn400 = Utils.getPersianDateFrom8Digit("1400/08/12");
//
//        Date azr4002 = Utils.getPersianDateFrom8Digit("1400/09/12");
//        Date azr400 = Utils.getPersianDateFrom8Digit("1400/09/12");
//
//        Date dey4002 = Utils.getPersianDateFrom8Digit("1400/10/10");
//        Date dey400 = Utils.getPersianDateFrom8Digit("1400/10/10");
//
//        Date bah4002 = Utils.getPersianDateFrom8Digit("1400/11/11");
//        Date bah400 = Utils.getPersianDateFrom8Digit("1400/11/11");
//
//        Date esf4002 = Utils.getPersianDateFrom8Digit("1400/12/12");
//        Date esf400 = Utils.getPersianDateFrom8Digit("1400/12/12");
//
//
//        List<Date> dates = Arrays.asList(
//                far400, far4002,
//                ord400, ord4002,
//                khor400, khor4002,
//                tir400, tir4002,
//                mor400, mor4002,
//                shar400, shar4002,
//                mhr400, mhr4002,
//                abn400, abn4002,
//                azr400, azr4002,
//                dey400, dey4002,
//                bah400, bah4002,
//                esf400, esf4002
//        );
//
//        for (Date date : dates) {
//            Cheque save = chequeRepository.save(chq);
//            Cheque cheque = save.setCreationTime(date);
//            chequeRepository.save(cheque);
//        }
//
//        for (Date date : dates) {
//            Paya save = payaRepository.save(paya);
//            Paya paya1 = save.setCreationTime(date);
//            payaRepository.save(paya1);
//        }
//
//    }
//
//
//    @Autowired
//    TransactionService transactionService;
//
//    @Test
//    void getAllMonthsInAYearSumOfTransactions() {
//        transactionService.getAllMonthsInAYearSumOfTransactionsFake();
//    }
//
//
//// 1.     autowiring the persistence context
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    private boolean isArrayNull(List list) {
//        return !(Objects.isNull(list) && list.isEmpty());
//    }
//
//    @Test
//    void checkThePackageWithTheValues() {
//        List<MonthNumberAndAmount> yearAndAmount = transactionService.linearChartByInstitute(1401L, 1459L);
//        if (isArrayNull(yearAndAmount))
//            for (MonthNumberAndAmount monthNumberAndAmount : yearAndAmount) {
//                System.out.println(monthNumberAndAmount.getMonthName());
//                System.out.println(monthNumberAndAmount.getAmount());
//            }
//    }
//
//
//    @Test
//    void checkThePackageWithTheValuesWithoutParameter() {
//        List<MonthNumberAndAmount> yearAndAmount = transactionService.linearChartByInstitute(1401L);
//        if (isArrayNull(yearAndAmount))
//            for (MonthNumberAndAmount monthNumberAndAmount : yearAndAmount) {
//                System.out.println(monthNumberAndAmount.getMonthName());
//                System.out.println(monthNumberAndAmount.getAmount());
//            }
//    }
//
////    private String getTxAggregatedAmountByMonth(Long year, Long instituteId) {
////
////        StoredProcedureQuery procedureQuery = entityManager.createStoredProcedureQuery("TAB17.PKG_TX_SERVICES.PRC_GETTXAGGREGATEDAMOUNTBYMONTH");
////
////        procedureQuery.registerStoredProcedureParameter("p_in_year", Long.class, ParameterMode.IN);
////        procedureQuery.registerStoredProcedureParameter("p_in_institute_id", Long.class, ParameterMode.IN);
////        procedureQuery.registerStoredProcedureParameter("p_Out_AggregatedAmountByMonth", String.class, ParameterMode.OUT);
////        procedureQuery.registerStoredProcedureParameter("p_response_code", Long.class, ParameterMode.OUT);
////        procedureQuery.registerStoredProcedureParameter("p_response_desc", String.class, ParameterMode.OUT);
////        procedureQuery.setParameter("p_in_year", year);
////        procedureQuery.setParameter("p_in_institute_id", instituteId);
////        procedureQuery.execute();
////        String p_Out_AggregatedAmountByMonth = (String) procedureQuery.getOutputParameterValue("p_Out_AggregatedAmountByMonth");
////
////        Long p_response_code = (Long) procedureQuery.getOutputParameterValue("p_response_code");
////        String p_response_desc = (String) procedureQuery.getOutputParameterValue("p_response_desc");
////
////        List<MonthNumberAndAmount> monthNumberAndAmounts = new ArrayList<>();
////        Gson gson = new Gson();
////        Type type = new TypeToken<List<MonthNumberAndAmount>>() {
////        }.getType();
////        List<MonthNumberAndAmount> data = gson.fromJson(p_Out_AggregatedAmountByMonth, type);
////
////        data.forEach(
////                numberAndAmount -> {
//////
////                    System.out.println(numberAndAmount.getAmount());
////                }
////        );
////
////
////        return p_Out_AggregatedAmountByMonth;
////
////    }
//
////    private String getTxTypePercentageShare(Long year, Long instituteId) {
////        StoredProcedureQuery procedureQuery = entityManager.createStoredProcedureQuery("TAB17.PKG_TX_SERVICES.PRC_GETTXTYPE_PERCENTAGESHARE");
////
////        procedureQuery.registerStoredProcedureParameter("p_in_year", Long.class, ParameterMode.IN);
////        procedureQuery.registerStoredProcedureParameter("p_in_institute_id", Long.class, ParameterMode.IN);
////        procedureQuery.registerStoredProcedureParameter("p_Out_TxType_PercentageShare", String.class, ParameterMode.OUT);
////        procedureQuery.registerStoredProcedureParameter("p_response_code", Long.class, ParameterMode.OUT);
////        procedureQuery.registerStoredProcedureParameter("p_response_desc", String.class, ParameterMode.OUT);
////        procedureQuery.setParameter("p_in_institute_id", instituteId);
////        procedureQuery.setParameter("p_in_year", year);
////        procedureQuery.execute();
////        String p_Out_TxType_PercentageShare = (String) procedureQuery.getOutputParameterValue("p_Out_TxType_PercentageShare");
////        System.out.println(p_Out_TxType_PercentageShare);
////        Long p_response_code = (Long) procedureQuery.getOutputParameterValue("p_response_code");
////        String p_response_desc = (String) procedureQuery.getOutputParameterValue("p_response_desc");
////
////        List<TxTypeAndPercent> monthNumberAndAmounts = new ArrayList<>();
////        Gson gson = new Gson();
////        Type type = new TypeToken<List<TxTypeAndPercent>>() {
////        }.getType();
////        List<TxTypeAndPercent> data = gson.fromJson(p_Out_TxType_PercentageShare, type);
////
////        data.forEach(
////                numberAndAmount -> {
////                    System.out.println(numberAndAmount.getCount_Percent());
////                    System.out.println(numberAndAmount.getTx_Type());
////                }
////        );
////
////
////        return p_Out_TxType_PercentageShare;
////
////    }
//
//
//    private String getCityListForTheProvinceCde(Long province_code) {
//        StoredProcedureQuery getCityList = entityManager.createStoredProcedureQuery("TAB17.PKG_COUNTRYDIVISIONS.PRC_GETCITYLIST");
//
//        getCityList.registerStoredProcedureParameter("p_in_ProvinceCode", Long.class, ParameterMode.IN);
////                p_in_ProvinceCode IN NUMBER
//        getCityList.registerStoredProcedureParameter("p_Out_CityList", String.class, ParameterMode.OUT);
////                p_Out_CityList    OUT CLOB
//        getCityList.registerStoredProcedureParameter("p_response_code", Long.class, ParameterMode.OUT);
////                p_response_code   OUT NUMBER
//        getCityList.registerStoredProcedureParameter("p_response_desc", String.class, ParameterMode.OUT);
////                p_response_desc   OUT VARCHAR2
//
//
//        getCityList.setParameter("p_in_ProvinceCode", 21L);
////                setting the parameters
//
//        getCityList.execute();
////                executing query
//        String p_Out_CityList = (String) getCityList.getOutputParameterValue("p_Out_CityList");
//        Long p_response_code = (Long) getCityList.getOutputParameterValue("p_response_code");
//        String p_response_desc = (String) getCityList.getOutputParameterValue("p_response_desc");
//
//        List<CityCodeAndName> cityCodeAndNames = new ArrayList<>();
//        Gson gson = new Gson();//exchanging data to object from json
//        Type type = new TypeToken<List<CityCodeAndName>>() {
//        }.getType();//get type of the object
//        List<CityCodeAndName> data = gson.fromJson(p_Out_CityList, type);//get data in object type
////print out data
//        data.forEach(
//                cityCodeAndName -> {
//                    System.out.println(cityCodeAndName.getName());
//                    System.out.println(cityCodeAndName.getCode());
//                }
//        );
//
//
//        return p_Out_CityList;
//    }
//
//    @Autowired
//    GetProvinceStoreProcedureService getProvinceStoreProcedureService;
//
//    @Test
//    public void getAllProvince() {
//
//        List<ProvinceProcedureDTO> provinceListForTheCityCde = getProvinceStoreProcedureService.getProvinceListForTheCityCde();
//        for (ProvinceProcedureDTO provinceResponseDto : provinceListForTheCityCde) {
//            System.out.println(provinceResponseDto.getName());
//            System.out.println(provinceResponseDto.getCode());
//        }
//
//    }
//
//    @Test
//    public void getTX() {
//        String nextTxTrackingCodeProcedure = transactionService.GetNextTxTrackingCodeProcedure(4L);
//        System.out.println(nextTxTrackingCodeProcedure);
//    }
//
//
////    @Test
////    void testServiceLineChartReport(){
////        List<MonthNumberAndAmount> yearAndAmount = transactionService.getYearAndAmount(1401L);
////        for (MonthNumberAndAmount monthNumberAndAmount : yearAndAmount) {
////            String s = convertMonthNumbertoPersianName(monthNumberAndAmount.getMonth_No());
////            monthNumberAndAmount.setMonth_No(s);
////            System.out.println(monthNumberAndAmount);
////
////        }
////        }
//
////    static String convertMonthNumbertoPersianName(String month_no) {
////        PersianMonth[] values = PersianMonth.values();
////        Integer value = Integer.valueOf(month_no);
////        String persianName = values[value - 1].getPersianName();
////        System.out.println(persianName);
////        return persianName;
////    }
//
//    @Autowired
//    EcoInfoService ecoInfoService;
//
//    @Test
//    public void testRightelDecileInfoService() throws IOException, InterruptedException {
//
//        IVRResponseToken ivrResponseToken = ecoInfoService.authForIvr();
//        IVRResponseDecile ivrResponseDecile = ecoInfoService.getDecile(ivrResponseToken, "0083959041");
//
//        System.out.println(ivrResponseDecile.getNewDecile());
//
//
//    }
//
//    @Test
//    void okhttp() throws IOException {
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(mediaType, "{\r\n\"userName\":\"07269676874656c2e6972\",\r\n\"password\":\"6d2e73686f68726174694\"\r\n}");
//        Request request = new Request.Builder()
//                .url("http://172.19.120.5:8080/api/v1/auth/login")
//                .method("POST", body)
//                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkNGVjYzk0OC1kZDRjLTRlMTYtYTQ1MS1mNTBkYzc4ZDc0NjkiLCJleHAiOjE2NTA3ODQ3Nzl9.0zxpO4XPLOTVO1kYZo6oRdx0jsy_AOZkpkVufO5Ktyc")
//                .addHeader("Content-Type", "application/json")
//                .build();
//        Response response = client.newCall(request).execute();
//        System.out.println(response.body().string());
//
//    }
//
//    @Test
//    void testCreationTimeForConversation() {
//        Conversation ent = new Conversation();
//        ent.setTitle("test2023");
//        Conversation save = conversationRepository.save(ent);
//        System.out.println(save.getCreationTime());
//
//    }
//
//
//    @Test
//    void testSortingInConversation() {
//        Optional<User> byId = userRepository.findById(1439L);
//        Set<User> users = new HashSet<>();
//        users.add(byId.get());
//        Page<Conversation> byUsersInOrderByCreationTimeAsc = conversationRepository.findByUsersInOrderByUpdateTimeDesc(users, PageRequest.of(0, 10));
//        byUsersInOrderByCreationTimeAsc.forEach(conversation -> {
//            System.out.print(conversation.getCreationTime());
//            System.out.println(conversation.getTitle());
//        });
//
//    }
//
//    @Test
//    void testSortingInConversationUpdateTime() {
//        Optional<User> byId = userRepository.findById(1439L);
//        Set<User> users = new HashSet<>();
//        users.add(byId.get());
//        Page<Conversation> a = conversationRepository.findByUsersInOrderByUpdateTimeDesc(users, PageRequest.of(0, 10));
//        a.forEach(conversation -> {
//            System.out.print(conversation.getUpdateTime());
//            System.out.println(conversation.getTitle());
//        });
//
//    }
//
//}
//
//
////class fastTest {
////    public static void main(String[] args) {
//////        extracted();
////        fourPercentGenerate();
////    }
////
////    private static void fourPercentGenerate() {
////        Random random = new Random();
////        double y = random.nextDouble();
////
////        double x = y * 100;
////        double v1 = random.nextDouble();
////        double v2 = v1 * x;
////        double v3 = x - v2;
////        double v = 100 - x;
////        double v4 = random.nextDouble();
////        double v5 = v4 * v;
////        double v6 = v - v5;
////        System.out.println(v6);
////        System.out.println(v5);
////        System.out.println(v2);
////        System.out.println(v3);
////        System.out.println(v6 + v5 + v2 + v3);
////    }
////
////    private static void extracted() {
////        PersianMonth valueOf = PersianMonth.valueOf("ABAN");
////        PersianMonth[] values = PersianMonth.values();
////        System.out.println(values[2].getPersianName());
////    }
////
////    @Autowired
////    TicketService ticketService;
////
////    @Test
////    void getFileSize() {
////
////        Path fileStoragePath = get("all tasks.xlsx").toAbsolutePath().normalize();
////        try {
////            byte[] bytes = Files.readAllBytes(fileStoragePath);
//////            MockMultipartFile firstFile = new MockMultipartFile("all tasks", "all tasks.xlsx", "text/plain", bytes);
////            System.out.println(ticketService.getFileSize(fileStoragePath));
////        } catch (IOException e) {
////            throw new RuntimeException(e);
////        }
////    }
////}
//
//
