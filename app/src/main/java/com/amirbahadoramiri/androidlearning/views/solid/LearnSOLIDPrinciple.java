package com.amirbahadoramiri.androidlearning.views.solid;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;
import com.amirbahadoramiri.androidlearning.views.solid.DependencyInversion.Chatter;
import com.amirbahadoramiri.androidlearning.views.solid.DependencyInversion.Green;
import com.amirbahadoramiri.androidlearning.views.solid.DependencyInversion.Mongo;
import com.amirbahadoramiri.androidlearning.views.solid.DependencyInversion.Room;
import com.amirbahadoramiri.androidlearning.views.solid.InterfaceSegregation.Cat;
import com.amirbahadoramiri.androidlearning.views.solid.InterfaceSegregation.Owl;
import com.amirbahadoramiri.androidlearning.views.solid.LiskovSubstitution.Barber;
import com.amirbahadoramiri.androidlearning.views.solid.LiskovSubstitution.Driver;
import com.amirbahadoramiri.androidlearning.views.solid.LiskovSubstitution.Person;
import com.amirbahadoramiri.androidlearning.views.solid.LiskovSubstitution.WhoIsThis;
import com.amirbahadoramiri.androidlearning.views.solid.OpenClosed.English;
import com.amirbahadoramiri.androidlearning.views.solid.OpenClosed.Persian;
import com.amirbahadoramiri.androidlearning.views.solid.OpenClosed.Sayer;
import com.amirbahadoramiri.androidlearning.views.solid.SingleResponsibility.Email;
import com.amirbahadoramiri.androidlearning.views.solid.SingleResponsibility.EmailServices;
import com.amirbahadoramiri.androidlearning.views.solid.SingleResponsibility.User;

public class LearnSOLIDPrinciple extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeColor();
        setContentView(R.layout.activity_learn_solidprinciple);
        setViewCompat();


//        Single Responsibility Principle (SRP)
//        این قانون میگه هر کلاس یا تابع باید برای یک کار مشخص ساخته شده و برای ویرایش شدن فقط یک دلیل داشته باشد
//        در غیر این صورت باید به چند کلاس یا تابع دیگر تقسیم شود
//        کلاس User فقط اطلاعات کاربر را نگه میدارد
//        کلاس EmailServices فقط کار ارسال ایمیل را انجام میدهد
//        کلاس Email نحوه کار با کلاس EmailServices را هندل میکند
//        کلاس User و کلاس EmailServices هیچ وابستگی به یکدیگر ندارند و ارتباط انها در Email برقرار میشود
//        User user = new User("amir","amir@gmail.com");
//        EmailServices emailServices = new EmailServices();
//        Email email = new Email(emailServices);
//        email.sendEmail(user,"");



//        Open-Closed Principle (OCP)
//        این قانون میگه در صورت نیاز برای توسعه نیازی به تغییر تابع sayHello یا کلاس Sayyer نیست
//        برنامه باید طوری چیشده بشه که برای توسعه ، کدهای قبلی ما دچار تغییر نشن !
//        کلاس Sayyer همیشه کار یکسانی دارد و نیازی به تغییر ندارد
//        کلاس های مربوط به زبان ها ، میتوانند توسعه یابند یا زبان دیگری ساخته شود
//        بدون آنکه نیازی به تغییر باقی توابع یا باقی کلاس ها باشد
//        Sayer sayer = new Sayer();
//        sayer.sayHello(new Persian());
//        sayer.sayHello(new English());



//        Liskov Substitution Principle (LSP)
//        این قانون میگه اگر کلاس Person داشته باشیم
//        و کلاس Barber و Driver از کلاس Person مشتق گرفته بشن
//        هر جایی که از کلاس Person استفاده شده میتوانیم به جاش از 2 کلاس دیگر استفاده کنیم
//        به شرط انکه کلاس های مشتق گرفته شده رفتار کلاس پدر را تغییر ندهند
//        Person person = new Person() {
//            @Override
//            public String job() {
//                return "Kashef";
//            }
//        };
//        person.setName("Mahmoud");
//        Driver driver = new Driver();
//        driver.setName("Reza");
//        Barber barber = new Barber();
//        barber.setName("Majid");
//        WhoIsThis.who(person);
//        WhoIsThis.who(driver);
//        WhoIsThis.who(barber);



//        Interface Segregation Principle (ISP)
//        معنی این قانون میشه جداسازی رابط ها یا اینترفیس ها
//        این اصل میگه اگر چند کلاس نیاز به اینترفیس داشتن
//        در صورتی که همه نیازهای این کلاس ها یکسان بود ، از اینترفیس یکسانی استفاده شود
//        اما در صورتی که نیازهای کلاس ها به اینترفیس ها یکسان نبود
//        به ازای هر کلاس متفاوت باید اینتفریس متفاوت ساخته شود و از کلی نویسی جلوگیری شود
//        هیچ کلاسی نباید توابعی که به انها احتیاجی ندارد را implement کند
//        این قانون هم برای interface ها هستش و هم برای abstract class ها
//        به عنوان مثال گربه Cat و جغد Owl هر دو حیوان هستند و اینترفیس حیوان را ایمپلیمنت میکنند
//        اما اگر متد fly را در Animal قرار میدادیم ، گربه قابلیت پرواز نداشت
//        به همین دلیل اینترفیس FlyableAnimal را برای قابلیت پرواز ساختیم تا Owl بتواند پرواز کند
//        Cat cat = new Cat();
//        cat.eat();
//        cat.run();
//        Owl owl = new Owl();
//        owl.fly();
//        owl.eat();
//        owl.run();



//        Dependency Inversion Principle (DIP)
//        این قانون میگه در پروژه کلاس ها به 2 دسته سطح پایین و سطح بالا تقسیم میشن
//        کلاس های سطح پایین ، کارهای پایه رو میکنن ، مثلا کار با دیتابیس رو هندل میکنن
//        کلاس های سطح بالای ، از کلاس های سطح پایین استفاده میکنن برای انجام امور اپلیکیشن
//        این قانون میگه کلاس های سطح پایین و سطح بالا نباید به یکدیگر وابسته باشن
//        یعنی implement یا extend نشده باشن و به یکدیگر پاس داده نشن
//        در ازاش کلاس های انتزاعی abstract class یا interface ها پاس داده بشن
//        در اینجا Green و Mongo و Room انواع کلاس های دیتابیس هستن
//        همه اینها اینترفیس Database رو دنبال میکنن
//        کلاس چتر برای چت کردن درست شده و نیاز به دیتابیس داره برای ذخیره سازی
//        به جای اینکه ورودی setDatabase از نوع کلاس اون باشه
//        انتزاع اون رو پاس میدیم یعنی کلاس Database رو که یا اینترفیس عه یا abstract class
//        و با اینکار میتونیم در کلاس chatter از هر نوع دیتابیسی که نیاز داشته باشیم استفاده کنیم
//        هم قابلیت توسعه داره و هم اینکه قابلیت تغییر دیتابیس در هر زمان وجود داره
//        Green green = new Green();
//        Mongo mongo = new Mongo();
//        Room room = new Room();
//        Chatter chatter = new Chatter();
//        chatter.setDatabase(green);
//        chatter.setDatabase(mongo);
//        chatter.setDatabase(room);
//        chatter.insert();
//        chatter.read();
//        chatter.update();

    }
}