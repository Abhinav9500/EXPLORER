package com.example.explorer;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PrivacypolicyActivity extends AppCompatActivity {
    TextView privacytext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacypolicy);
        privacytext = findViewById(R.id.privacytext);
        privacytext.setText("Privacy Policy\n\n\n" +
                "Team EXPLORER built this app as a Free app. This SERVICE is provided by Team EXPLORER at no cost and is intended for use as is.\n" +
                "\n" +
                "This page is used to inform visitors regarding my policies with the collection, use, and disclosure of Personal Information if anyone decided to use my Service.\n" +
                "\n" +
                "If you choose to use my Service, then you agree to the collection and use of information in relation to this policy. The Personal Information that I collect is used for providing and improving the Service. I will not use or share your information with anyone except as described in this Privacy Policy.\n" +
                "\n" +
                "The terms used in this Privacy Policy have the same meanings as in our Terms and Conditions, which is accessible EXPLORER application unless otherwise defined in this Privacy Policy.\n" +
                "\n" +
                "Information Collection and Use\n" +
                "\n" +
                "For a better experience, while using our Service, I may require you to provide us with certain personally identifiable information. The information that I request will be retained on your device and is not collected by me in any way.\n" +
                "\n" +
                "The app does use third party services that may collect information used to identify you.\n" +
                "\n" +
                "Link to privacy policy of third party service providers used by the app\n" +
                "\n" +
                "Google Play Services\n" +
                "Log Data\n" +
                "\n" +
                "We want to inform you that whenever you use our Service, in a case of an error in the app I collect data and information (through third party products) on your phone called Log Data. This Log Data may include information such as your device Internet Protocol (“IP”) address, device name, operating system version, the configuration of the app when utilizing my Service, the time and date of your use of the Service, and other statistics.\n" +
                "\n" +
                "Service Providers\n" +
                "\n" +
                "We may employ third-party companies and individuals due to the following reasons:\n" +
                "\n" +
                "To facilitate our Service;\n" +
                "To provide the Service on our behalf;\n" +
                "To perform Service-related services; or\n" +
                "To assist us in analyzing how our Service is used.\n" +
                "We want to inform users of this Service that these third parties have access to your Personal Information. The reason is to perform the tasks assigned to them on our behalf. However, they are obligated not to disclose or use the information for any other purpose.\n" +
                "\n" +
                "Security\n" +
                "\n" +
                "I value your trust in providing us your Personal Information, thus we are striving to use commercially acceptable means of protecting it. But remember that no method of transmission over the internet, or method of electronic storage is 100% secure and reliable, and I cannot guarantee its absolute security.\n" +
                "\n" +
                "Links to Other Sites\n" +
                "\n" +
                "This Service may contain links to other sites. If you click on a third-party link, you will be directed to that site. Note that these external sites are not operated by me. Therefore, I strongly advise you to review the Privacy Policy of these websites. I have no control over and assume no responsibility for the content, privacy policies, or practices of any third-party sites or services.\n" +
                "\n" +
                "Children’s Privacy\n" +
                "\n" +
                "These Services do not address anyone under the age of 13. We do not knowingly collect personally identifiable information from children under 13. In the case I discover that a child under 13 has provided me with personal information, I immediately delete this from our servers. If you are a parent or guardian and you are aware that your child has provided us with personal information, please contact me so that I will be able to do necessary actions.\n" +
                "\n" +
                "Changes to This Privacy Policy\n" +
                "\n" +
                "I may update our Privacy Policy from time to time. Thus, you are advised to review this page periodically for any changes. I will notify you of any changes by posting the new Privacy Policy on this page. These changes are effective immediately after they are posted on this page.\n" +
                "\n" +
                "Contact Us\n" +
                "\n" +
                "If you have any questions or suggestions about our Privacy Policy, do not hesitate to contact us thourgh feedback.\n" +
                "\n" +
                "This privacy policy page was created at privacypolicytemplate.net and modified/generated by App Privacy Policy Generator\n");

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }

    }
}