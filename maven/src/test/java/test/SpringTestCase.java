package test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:../../WEB-INF/web.xml","classpath:*.xml"})
@Transactional
@Controller
public class SpringTestCase extends AbstractJUnit4SpringContextTests {//这个是
    @Autowired
    WebApplicationContext wac;
    MockMvc mockMvc;
    @Before()  //这个方法在每个方法执行之前都会执行一遍
    public void Before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
    }
//	@Test
//    public void test() {
//		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
//		IniSecurityManagerFactory factory1 = new IniSecurityManagerFactory();
//
//		SecurityManager securityManager = factory.getInstance();
//		SecurityUtils.setSecurityManager(securityManager);
//		UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
//		token.setRememberMe(true);
//		token.getPrincipal();
//		// get the currently executing user:
//		Subject currentUser = SecurityUtils.getSubject();
//		MyCalBack o = null;
//		try {
//			o = (MyCalBack)Class.forName(MyCalBack.class.getName()).newInstance();
//			Long add = o.Add(1, 2, (x,y)->{
//				return x + y;
//			});
//			System.out.println(add);
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		Proxy.newProxyInstance(super.getClass().getClassLoader(), o.getClass().getInterfaces(), new InvocationHandler() {
//			@Override
//			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//				return null;
//			}
//		});
//
//		Long c = new MyCalBack().Add(1, 2, new MyFunction<Long, Long>() {
//			@Override
//			public Long hander(Long a, Long b) {
//				return a + b;
//			}
//		});
//		System.out.println(c);
//
//		new MyCalBack().setCallBackFunction(new MyCalBack.CallBack<String>() {
//			@Override
//			public String UserFunctionCB() {
//				return "Hello Call Back Function!";
//			}
//		});
//
//List<MyCalBack> list = Arrays.asList(
//new MyCalBack("刘亚壮", 20),
//new MyCalBack("张三", 26),
//new MyCalBack("李四", 27),
//new MyCalBack("小明", 18),
//new MyCalBack("小李", 30)
//);
//String name = "小明";
//String age = "18";
//List<MyCalBack> persons = new MyCalBack().Add(name, age, (x, y) -> {
//	List<MyCalBack> subPersons = list.stream().filter(p -> p.getAge() >= Integer.parseInt(y) && p.getName().equals(x)).collect(Collectors.toList());
//	return subPersons;
//});
//persons.stream().forEach(System.out::println);
//Stream<MyCalBack> stream = persons.stream();

/*
createImage image = new createImage();
image.create();
String fileName = "/Users/cgh/Desktop/20190409.txt";
String orderId = "";//最后的结果
String Separate = ",";//分隔符号
int i = 3;//多少行
BufferedReader br = null;
try {
String line = "";
FileReader reader = new FileReader(fileName);
br = new BufferedReader(reader);
while ((line = br.readLine()) != null && i-- > 0) {
orderId += Separate + line;
}
orderId = orderId.replaceFirst(",", "");
} catch (FileNotFoundException e) {
e.printStackTrace();
} catch (IOException e) {
e.printStackTrace();
}finally {
try {
br.close();
} catch (IOException e) {
}
}
ArithmeticUtils.gcd(1, 2);
String S = ZhConverterUtil.convertToTraditional("生命在于运动");
ArrayList<String> list = new ArrayList<String>();
list.add("1");
list.add("222");
System.out.println(StringUtils.join(list, ","));
String ss = StringEscapeUtils.escapeJava("\"sss");
System.out.println(ss);
ForkJoinPool pool = new ForkJoinPool(5);
pool.
pool.execute(bTest);
bTest.isDone();
bTest clone = clone();
System.err.println(clone);

List<String> list = new CopyOnWriteArrayList<String>();
ArrayDeque<String> deque = new ArrayDeque<String>();
deque.add("c");
System.err.println(deque.peekLast());
System.err.println(deque.size());
ForkJoinPool forkJoinPool = new ForkJoinPool();

ConcurrentLinkedDeque<String> linkedDeque = new ConcurrentLinkedDeque<String>();
linkedDeque.add("c");
System.err.println(linkedDeque.isEmpty());
ArrayDeque<String> arrayDeque = new ArrayDeque<String>();
System.err.println(arrayDeque.isEmpty());
*/

//	}
}
