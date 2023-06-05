package innui.modelos.errores;

import android.innui.modelos_android.configuraciones.ResourceBundles;
import innui.modelos.internacionalizacion.tr;
import innui.bases;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class patrones extends bases {
    public static String k_in_ruta = "assets/in/innui/modelos/errores/in";

    public static String k_patrones_iana_dominios_alto_nivel =
        "(?:"
        + "(?:aaa|aarp|abb|abbott|abogado|academy|accenture|accountant|accountants|aco|active"
        + "|actor|ads|adult|aeg|aero|afl|agency|aig|airforce|airtel|allfinanz|alsace|amica|amsterdam"
        + "|android|apartments|app|apple|aquarelle|aramco|archi|army|arpa|arte|asia|associates"
        + "|attorney|auction|audio|auto|autos|axa|azure|a[cdefgilmoqrstuwxz])"
        + "|(?:band|bank|bar|barcelona|barclaycard|barclays|bargains|bauhaus|bayern|bbc|bbva"
        + "|bcn|beats|beer|bentley|berlin|best|bet|bharti|bible|bid|bike|bing|bingo|bio|biz|black"
        + "|blackfriday|bloomberg|blue|bms|bmw|bnl|bnpparibas|boats|bom|bond|boo|boots|boutique"
        + "|bradesco|bridgestone|broadway|broker|brother|brussels|budapest|build|builders|business"
        + "|buzz|bzh|b[abdefghijmnorstvwyz])"
        + "|(?:cab|cafe|cal|camera|camp|cancerresearch|canon|capetown|capital|car|caravan|cards"
        + "|care|career|careers|cars|cartier|casa|cash|casino|cat|catering|cba|cbn|ceb|center|ceo"
        + "|cern|cfa|cfd|chanel|channel|chat|cheap|chloe|christmas|chrome|church|cipriani|cisco"
        + "|citic|city|cityeats|claims|cleaning|click|clinic|clothing|cloud|club|clubmed|coach"
        + "|codes|coffee|college|cologne|com|commbank|community|company|computer|comsec|condos"
        + "|construction|consulting|contractors|cooking|cool|coop|corsica|country|coupons|courses"
        + "|credit|creditcard|creditunion|cricket|crown|crs|cruises|csc|cuisinella|cymru|cyou|c[acdfghiklmnoruvwxyz])"
        + "|(?:dabur|dad|dance|date|dating|datsun|day|dclk|deals|degree|delivery|dell|delta"
        + "|democrat|dental|dentist|desi|design|dev|diamonds|diet|digital|direct|directory|discount"
        + "|dnp|docs|dog|doha|domains|doosan|download|drive|durban|dvag|d[ejkmoz])"
        + "|(?:earth|eat|edu|education|email|emerck|energy|engineer|engineering|enterprises"
        + "|epson|equipment|erni|esq|estate|eurovision|eus|events|everbank|exchange|expert|exposed"
        + "|express|e[cegrstu])"
        + "|(?:fage|fail|fairwinds|faith|family|fan|fans|farm|fashion|feedback|ferrero|film"
        + "|final|finance|financial|firmdale|fish|fishing|fit|fitness|flights|florist|flowers|flsmidth"
        + "|fly|foo|football|forex|forsale|forum|foundation|frl|frogans|fund|furniture|futbol|fyi"
        + "|f[ijkmor])"
        + "|(?:gal|gallery|game|garden|gbiz|gdn|gea|gent|genting|ggee|gift|gifts|gives|giving"
        + "|glass|gle|global|globo|gmail|gmo|gmx|gold|goldpoint|golf|goo|goog|google|gop|gov|grainger"
        + "|graphics|gratis|green|gripe|group|gucci|guge|guide|guitars|guru|g[abdefghilmnpqrstuwy])"
        + "|(?:hamburg|hangout|haus|healthcare|help|here|hermes|hiphop|hitachi|hiv|hockey|holdings"
        + "|holiday|homedepot|homes|honda|horse|host|hosting|hoteles|hotmail|house|how|hsbc|hyundai"
        + "|h[kmnrtu])"
        + "|(?:ibm|icbc|ice|icu|ifm|iinet|immo|immobilien|industries|infiniti|info|ing|ink|institute"
        + "|insure|int|international|investments|ipiranga|irish|ist|istanbul|itau|iwc|i[delmnoqrst])"
        + "|(?:jaguar|java|jcb|jetzt|jewelry|jlc|jll|jobs|joburg|jprs|juegos|j[emop])"
        + "|(?:kaufen|kddi|kia|kim|kinder|kitchen|kiwi|koeln|komatsu|krd|kred|kyoto|k[eghimnprwyz])"
        + "|(?:lacaixa|lancaster|land|landrover|lasalle|lat|latrobe|law|lawyer|lds|lease|leclerc"
        + "|legal|lexus|lgbt|liaison|lidl|life|lifestyle|lighting|limited|limo|linde|link|live"
        + "|lixil|loan|loans|lol|london|lotte|lotto|love|ltd|ltda|lupin|luxe|luxury|l[abcikrstuvy])"
        + "|(?:madrid|maif|maison|man|management|mango|market|marketing|markets|marriott|mba"
        + "|media|meet|melbourne|meme|memorial|men|menu|meo|miami|microsoft|mil|mini|mma|mobi|moda"
        + "|moe|moi|mom|monash|money|montblanc|mormon|mortgage|moscow|motorcycles|mov|movie|movistar"
        + "|mtn|mtpc|mtr|museum|mutuelle|m[acdeghklmnopqrstuvwxyz])"
        + "|(?:nadex|nagoya|name|navy|nec|net|netbank|network|neustar|new|news|nexus|ngo|nhk"
        + "|nico|ninja|nissan|nokia|nra|nrw|ntt|nyc|n[acefgilopruz])"
        + "|(?:obi|office|okinawa|omega|one|ong|onl|online|ooo|oracle|orange|org|organic|osaka"
        + "|otsuka|ovh|om)"
        + "|(?:page|panerai|paris|partners|parts|party|pet|pharmacy|philips|photo|photography"
        + "|photos|physio|piaget|pics|pictet|pictures|ping|pink|pizza|place|play|playstation|plumbing"
        + "|plus|pohl|poker|porn|post|praxi|press|pro|prod|productions|prof|properties|property"
        + "|protection|pub|p[aefghklmnrstwy])"
        + "|(?:qpon|quebec|qa)"
        + "|(?:racing|realtor|realty|recipes|red|redstone|rehab|reise|reisen|reit|ren|rent|rentals"
        + "|repair|report|republican|rest|restaurant|review|reviews|rich|ricoh|rio|rip|rocher|rocks"
        + "|rodeo|rsvp|ruhr|run|rwe|ryukyu|r[eosuw])"
        + "|(?:saarland|sakura|sale|samsung|sandvik|sandvikcoromant|sanofi|sap|sapo|sarl|saxo"
        + "|sbs|sca|scb|schmidt|scholarships|school|schule|schwarz|science|scor|scot|seat|security"
        + "|seek|sener|services|seven|sew|sex|sexy|shiksha|shoes|show|shriram|singles|site|ski"
        + "|sky|skype|sncf|soccer|social|software|sohu|solar|solutions|sony|soy|space|spiegel|spreadbetting"
        + "|srl|stada|starhub|statoil|stc|stcgroup|stockholm|studio|study|style|sucks|supplies"
        + "|supply|support|surf|surgery|suzuki|swatch|swiss|sydney|systems|s[abcdeghijklmnortuvxyz])"
        + "|(?:tab|taipei|tatamotors|tatar|tattoo|tax|taxi|team|tech|technology|tel|telefonica"
        + "|temasek|tennis|thd|theater|theatre|tickets|tienda|tips|tires|tirol|today|tokyo|tools"
        + "|top|toray|toshiba|tours|town|toyota|toys|trade|trading|training|travel|trust|tui|t[cdfghjklmnortvwz])"
        + "|(?:ubs|university|uno|uol|u[agksyz])"
        + "|(?:vacations|vana|vegas|ventures|versicherung|vet|viajes|video|villas|vin|virgin"
        + "|vision|vista|vistaprint|viva|vlaanderen|vodka|vote|voting|voto|voyage|v[aceginu])"
        + "|(?:wales|walter|wang|watch|webcam|website|wed|wedding|weir|whoswho|wien|wiki|williamhill"
        + "|win|windows|wine|wme|work|works|world|wtc|wtf|w[fs])"
        + "|(?:\u03b5\u03bb|\u0431\u0435\u043b|\u0434\u0435\u0442\u0438|\u043a\u043e\u043c|\u043c\u043a\u0434"
        + "|\u043c\u043e\u043d|\u043c\u043e\u0441\u043a\u0432\u0430|\u043e\u043d\u043b\u0430\u0439\u043d"
        + "|\u043e\u0440\u0433|\u0440\u0443\u0441|\u0440\u0444|\u0441\u0430\u0439\u0442|\u0441\u0440\u0431"
        + "|\u0443\u043a\u0440|\u049b\u0430\u0437|\u0570\u0561\u0575|\u05e7\u05d5\u05dd|\u0627\u0631\u0627\u0645\u0643\u0648"
        + "|\u0627\u0644\u0627\u0631\u062f\u0646|\u0627\u0644\u062c\u0632\u0627\u0626\u0631|\u0627\u0644\u0633\u0639\u0648\u062f\u064a\u0629"
        + "|\u0627\u0644\u0645\u063a\u0631\u0628|\u0627\u0645\u0627\u0631\u0627\u062a|\u0627\u06cc\u0631\u0627\u0646"
        + "|\u0628\u0627\u0632\u0627\u0631|\u0628\u06be\u0627\u0631\u062a|\u062a\u0648\u0646\u0633"
        + "|\u0633\u0648\u062f\u0627\u0646|\u0633\u0648\u0631\u064a\u0629|\u0634\u0628\u0643\u0629"
        + "|\u0639\u0631\u0627\u0642|\u0639\u0645\u0627\u0646|\u0641\u0644\u0633\u0637\u064a\u0646"
        + "|\u0642\u0637\u0631|\u0643\u0648\u0645|\u0645\u0635\u0631|\u0645\u0644\u064a\u0633\u064a\u0627"
        + "|\u0645\u0648\u0642\u0639|\u0915\u0949\u092e|\u0928\u0947\u091f|\u092d\u093e\u0930\u0924"
        + "|\u0938\u0902\u0917\u0920\u0928|\u09ad\u09be\u09b0\u09a4|\u0a2d\u0a3e\u0a30\u0a24|\u0aad\u0abe\u0ab0\u0aa4"
        + "|\u0b87\u0ba8\u0bcd\u0ba4\u0bbf\u0baf\u0bbe|\u0b87\u0bb2\u0b99\u0bcd\u0b95\u0bc8|\u0b9a\u0bbf\u0b99\u0bcd\u0b95\u0baa\u0bcd\u0baa\u0bc2\u0bb0\u0bcd"
        + "|\u0c2d\u0c3e\u0c30\u0c24\u0c4d|\u0dbd\u0d82\u0d9a\u0dcf|\u0e04\u0e2d\u0e21|\u0e44\u0e17\u0e22"
        + "|\u10d2\u10d4|\u307f\u3093\u306a|\u30b0\u30fc\u30b0\u30eb|\u30b3\u30e0|\u4e16\u754c"
        + "|\u4e2d\u4fe1|\u4e2d\u56fd|\u4e2d\u570b|\u4e2d\u6587\u7f51|\u4f01\u4e1a|\u4f5b\u5c71"
        + "|\u4fe1\u606f|\u5065\u5eb7|\u516b\u5366|\u516c\u53f8|\u516c\u76ca|\u53f0\u6e7e|\u53f0\u7063"
        + "|\u5546\u57ce|\u5546\u5e97|\u5546\u6807|\u5728\u7ebf|\u5927\u62ff|\u5a31\u4e50|\u5de5\u884c"
        + "|\u5e7f\u4e1c|\u6148\u5584|\u6211\u7231\u4f60|\u624b\u673a|\u653f\u52a1|\u653f\u5e9c"
        + "|\u65b0\u52a0\u5761|\u65b0\u95fb|\u65f6\u5c1a|\u673a\u6784|\u6de1\u9a6c\u9521|\u6e38\u620f"
        + "|\u70b9\u770b|\u79fb\u52a8|\u7ec4\u7ec7\u673a\u6784|\u7f51\u5740|\u7f51\u5e97|\u7f51\u7edc"
        + "|\u8c37\u6b4c|\u96c6\u56e2|\u98de\u5229\u6d66|\u9910\u5385|\u9999\u6e2f|\ub2f7\ub137"
        + "|\ub2f7\ucef4|\uc0bc\uc131|\ud55c\uad6d|xbox"
        + "|xerox|xin|xn\\-\\-11b4c3d|xn\\-\\-1qqw23a|xn\\-\\-30rr7y|xn\\-\\-3bst00m|xn\\-\\-3ds443g"
        + "|xn\\-\\-3e0b707e|xn\\-\\-3pxu8k|xn\\-\\-42c2d9a|xn\\-\\-45brj9c|xn\\-\\-45q11c|xn\\-\\-4gbrim"
        + "|xn\\-\\-55qw42g|xn\\-\\-55qx5d|xn\\-\\-6frz82g|xn\\-\\-6qq986b3xl|xn\\-\\-80adxhks"
        + "|xn\\-\\-80ao21a|xn\\-\\-80asehdb|xn\\-\\-80aswg|xn\\-\\-90a3ac|xn\\-\\-90ais|xn\\-\\-9dbq2a"
        + "|xn\\-\\-9et52u|xn\\-\\-b4w605ferd|xn\\-\\-c1avg|xn\\-\\-c2br7g|xn\\-\\-cg4bki|xn\\-\\-clchc0ea0b2g2a9gcd"
        + "|xn\\-\\-czr694b|xn\\-\\-czrs0t|xn\\-\\-czru2d|xn\\-\\-d1acj3b|xn\\-\\-d1alf|xn\\-\\-efvy88h"
        + "|xn\\-\\-estv75g|xn\\-\\-fhbei|xn\\-\\-fiq228c5hs|xn\\-\\-fiq64b|xn\\-\\-fiqs8s|xn\\-\\-fiqz9s"
        + "|xn\\-\\-fjq720a|xn\\-\\-flw351e|xn\\-\\-fpcrj9c3d|xn\\-\\-fzc2c9e2c|xn\\-\\-gecrj9c"
        + "|xn\\-\\-h2brj9c|xn\\-\\-hxt814e|xn\\-\\-i1b6b1a6a2e|xn\\-\\-imr513n|xn\\-\\-io0a7i"
        + "|xn\\-\\-j1aef|xn\\-\\-j1amh|xn\\-\\-j6w193g|xn\\-\\-kcrx77d1x4a|xn\\-\\-kprw13d|xn\\-\\-kpry57d"
        + "|xn\\-\\-kput3i|xn\\-\\-l1acc|xn\\-\\-lgbbat1ad8j|xn\\-\\-mgb9awbf|xn\\-\\-mgba3a3ejt"
        + "|xn\\-\\-mgba3a4f16a|xn\\-\\-mgbaam7a8h|xn\\-\\-mgbab2bd|xn\\-\\-mgbayh7gpa|xn\\-\\-mgbbh1a71e"
        + "|xn\\-\\-mgbc0a9azcg|xn\\-\\-mgberp4a5d4ar|xn\\-\\-mgbpl2fh|xn\\-\\-mgbtx2b|xn\\-\\-mgbx4cd0ab"
        + "|xn\\-\\-mk1bu44c|xn\\-\\-mxtq1m|xn\\-\\-ngbc5azd|xn\\-\\-node|xn\\-\\-nqv7f|xn\\-\\-nqv7fs00ema"
        + "|xn\\-\\-nyqy26a|xn\\-\\-o3cw4h|xn\\-\\-ogbpf8fl|xn\\-\\-p1acf|xn\\-\\-p1ai|xn\\-\\-pgbs0dh"
        + "|xn\\-\\-pssy2u|xn\\-\\-q9jyb4c|xn\\-\\-qcka1pmc|xn\\-\\-qxam|xn\\-\\-rhqv96g|xn\\-\\-s9brj9c"
        + "|xn\\-\\-ses554g|xn\\-\\-t60b56a|xn\\-\\-tckwe|xn\\-\\-unup4y|xn\\-\\-vermgensberater\\-ctb"
        + "|xn\\-\\-vermgensberatung\\-pwb|xn\\-\\-vhquv|xn\\-\\-vuq861b|xn\\-\\-wgbh1c|xn\\-\\-wgbl6a"
        + "|xn\\-\\-xhq521b|xn\\-\\-xkc2al3hye2a|xn\\-\\-xkc2dl3a5ee0h|xn\\-\\-y9a3aq|xn\\-\\-yfro4i67o"
        + "|xn\\-\\-ygbi2ammx|xn\\-\\-zfr164b|xperia|xxx|xyz)"
        + "|(?:yachts|yamaxun|yandex|yodobashi|yoga|yokohama|youtube|y[et])"
        + "|(?:zara|zip|zone|zuerich|z[amw]))";

    public static String k_patrones_direccion_ip_v4 =
            "((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4]"
            + "[0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]"
            + "[0-9]{2}|[1-9][0-9]|[1-9]|0))";

    public static String k_patrones_ucs_caracter = "[" +
            "\u00A0-\uD7FF" +
            "\uF900-\uFDCF" +
            "\uFDF0-\uFFEF" +
            "\uD800\uDC00-\uD83F\uDFFD" +
            "\uD840\uDC00-\uD87F\uDFFD" +
            "\uD880\uDC00-\uD8BF\uDFFD" +
            "\uD8C0\uDC00-\uD8FF\uDFFD" +
            "\uD900\uDC00-\uD93F\uDFFD" +
            "\uD940\uDC00-\uD97F\uDFFD" +
            "\uD980\uDC00-\uD9BF\uDFFD" +
            "\uD9C0\uDC00-\uD9FF\uDFFD" +
            "\uDA00\uDC00-\uDA3F\uDFFD" +
            "\uDA40\uDC00-\uDA7F\uDFFD" +
            "\uDA80\uDC00-\uDABF\uDFFD" +
            "\uDAC0\uDC00-\uDAFF\uDFFD" +
            "\uDB00\uDC00-\uDB3F\uDFFD" +
            "\uDB44\uDC00-\uDB7F\uDFFD" +
            "&&[^\u00A0[\u2000-\u200A]\u2028\u2029\u202F\u3000]]";

    public static String k_patrones_etiqueta_caracter = "a-zA-Z0-9" + k_patrones_ucs_caracter;

    public static String k_patrones_tld_caracter = "a-zA-Z" + k_patrones_ucs_caracter;

    public static String k_patrones_iri_etiqueta =
            "[" + k_patrones_etiqueta_caracter + "](?:[" + k_patrones_etiqueta_caracter + "_\\-]{0,61}[" + k_patrones_etiqueta_caracter + "]){0,1}";

    public static String k_patrones_punycode_tld = "xn\\-\\-[\\w\\-]{0,58}\\w";

    public static String k_patrones_tld = "(" + k_patrones_punycode_tld + "|" + "[" + k_patrones_tld_caracter + "]{2,63}" +")";

    public static String k_patrones_hostname = "(" + k_patrones_iri_etiqueta + "\\.)+" + k_patrones_tld;

    public static String k_patrones_nombre_dominio_str = "(" + k_patrones_hostname + "|" + k_patrones_direccion_ip_v4 + ")";
    public static String k_patrones_nombre_dominio = k_patrones_nombre_dominio_str;

    public static String k_patrones_protocolo = "(?i:http|https|rtsp|ftp)://";

    public static String k_patrones_limitador_palabra = "(?:\\b|$|^)";

    public static String k_patrones_user_info = "(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)"
            + "\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,64}(?:\\:(?:[a-zA-Z0-9\\$\\-\\_"
            + "\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,25})?\\@";

    public static String k_patrones_puerto_num = "\\:\\d{1,5}";

    public static String k_patrones_path_y_query = "[/\\?](?:(?:[" + k_patrones_etiqueta_caracter
            + ";/\\?:@&=#~"  
            + "\\-\\.\\+!\\*'\\(\\),_\\$])|(?:%[a-fA-F0-9]{2}))*";

    public static String k_patrones_uri = "("
            + "("
            + "(?:" + k_patrones_protocolo + "(?:" + k_patrones_user_info + ")?" + ")?"
            + "(?:" + k_patrones_nombre_dominio_str + ")"
            + "(?:" + k_patrones_puerto_num + ")?"
            + ")"
            + "(" + k_patrones_path_y_query + ")?"
            + k_patrones_limitador_palabra
            + ")";

    public static String k_patrones_tld_estricto = "(?:" +
            k_patrones_iana_dominios_alto_nivel + "|" + k_patrones_punycode_tld + ")";

    public static String k_patrones_hostname_estricto = "(?:(?:" + k_patrones_iri_etiqueta + "\\.)+"
            + k_patrones_tld_estricto + ")";

    public static String k_patrones_nombre_dominio_estricto = "(?:" + k_patrones_hostname_estricto + "|"
            + k_patrones_direccion_ip_v4 + ")";

    public static String k_patrones_nombre_de_dominio_relajado =
            "(?:" + "(?:" + k_patrones_iri_etiqueta + "(?:\\.(?=\\S))" +"?)+" + "|" + k_patrones_direccion_ip_v4 + ")";

    public static String k_patrones_web_url_sin_protocolo = "("
            + k_patrones_limitador_palabra
            + "(?<!:\\/\\/)"
            + "("
            + "(?:" + k_patrones_nombre_dominio_estricto + ")"
            + "(?:" + k_patrones_puerto_num + ")?"
            + ")"
            + "(?:" + k_patrones_path_y_query + ")?"
            + k_patrones_limitador_palabra
            + ")";

    public static String k_patrones_web_url_con_protocolo = "("
            + k_patrones_limitador_palabra
            + "(?:"
            + "(?:" + k_patrones_protocolo + "(?:" + k_patrones_user_info + ")?" + ")"
            + "(?:" + k_patrones_nombre_de_dominio_relajado + ")?"
            + "(?:" + k_patrones_puerto_num + ")?"
            + ")"
            + "(?:" + k_patrones_path_y_query + ")?"
            + k_patrones_limitador_palabra
            + ")";

    public static String k_patrones_web_url = 
            "(" + k_patrones_web_url_con_protocolo + "|" + k_patrones_web_url_sin_protocolo + ")";

    public static String k_patrones_email_caracter = k_patrones_etiqueta_caracter + "\\+\\-_%'";

    public static String k_patrones_email_parte_local =
            "[" + k_patrones_email_caracter + "]" + "(?:[" + k_patrones_email_caracter + "\\.]{0,62}[" + k_patrones_email_caracter + "])?";

    public static String k_patrones_email_dominio =
            "(?=.{1,255}(?:\\s|$|^))" + k_patrones_hostname;

    public static String k_patrones_autolink_email = "(" + k_patrones_limitador_palabra +
            "(?:" + k_patrones_email_parte_local + "@" + k_patrones_email_dominio + ")" +
            k_patrones_limitador_palabra + ")";

    public static String k_patrones_email =
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+";

    public static String k_patrones_telefono =
                "(\\+[0-9]+[\\- \\.]*)?"
                + "(\\([0-9]+\\)[\\- \\.]*)?"
                + "([0-9][0-9\\- \\.]+[0-9])";
    // (?:(?:31(\/|-|\.)(?:0?[13578]|1[0-2]))\1|(?:(?:29|30)(\/|-|\.)(?:0?[13-9]|1[0-2])\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})|(?:29(\/|-|\.)0?2\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))|(?:0?[1-9]|1\d|2[0-8])(\/|-|\.)(?:(?:0?[1-9])|(?:1[0-2]))\4(?:(?:1[6-9]|[2-9]\d)?\d{2})
//    public static String k_patrones_fecha_diamesaño = "(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[0-2]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})|(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))|(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})";
    // (\d{4}\-((((0[13578])|(1[02]))\-(([0-2][0-9])|(3[01])))|(((0[469])|(11))\-(([0-2][0-9])|(30)))|(02\-[0-2][0-9])))
//    public static String k_patrones_fecha_añomesdia = "(\\d{4}\\-((((0[13578])|(1[02]))\\-(([0-2][0-9])|(3[01])))|(((0[469])|(11))\\-(([0-2][0-9])|(30)))|(02\\-[0-2][0-9])))";
    public static String k_patrones_formato_fecha = "yyyy-MM-dd | dd/MM/yyyy | dd-MM-yyyy | dd.MM.yyyy";
    public static String k_patrones_hora = "(2[0-3]|[01]?[0-9]):([0-5]?[0-9])|(2[0-3]|[01]?[0-9]):([0-5]?[0-9]):([0-5]?[0-9])";
    public static String k_patrones_formato_hora = "HH:mm | HH:mm:ss";
    
    /**
     * Valida una fecha con los formatos k_patrones_fecha_diamesaño
     * @param texto
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public static boolean validar_fecha(String texto, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            ResourceBundle in;
//            String regex = "^(" + k_patrones_fecha_diamesaño + ")$";
//            texto = texto.trim();
//            ok.es = texto.matches(regex);
//            if (ok.es == false) {
//                regex = "^(" + k_patrones_fecha_añomesdia + ")$";
//                ok.es = texto.matches(regex);
//            }
            ok.es = (convertir_fecha(texto, ok, extras_array) != null);
            if (ok.es == false) {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in(in, "Error, no cumple con alguno de los formatos: ") + "(dd/mm/aaaa | dd-mm-aaaa | dd.mm.aaaa | aaaa-mm-dd)");
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Valida una fecha hora, con los formatos k_patrones_fecha_diamesaño + " " + k_patrones_hora
     * @param texto
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public static boolean validar_fecha_y_hora(String texto, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            ResourceBundle in;
//            String regex = "^(" + k_patrones_fecha_diamesaño + " " + k_patrones_hora + ")$";
//            texto = texto.trim();
//            ok.es = texto.matches(regex);
//            if (ok.es == false) {
//                regex = "^(" + k_patrones_fecha_añomesdia + "[Tt\\s]" + k_patrones_hora + ")$";
//                ok.es = texto.matches(regex);
//            }
            ok.es = (convertir_fecha_y_hora(texto, ok, extras_array) != null);
            if (ok.es == false) {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in(in, "Error, no cumple con alguno de los formatos: ") + "(dd/mm/aaaa | dd-mm-aaaa | dd.mm.aaaa | aaaa-mm-dd)[Tt ](hh:MM | hh:MM:ss)");
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Valida una hora con el formatos k_patrones_hora
     * @param texto
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public static boolean validar_hora(String texto, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            ResourceBundle in;
            String regex = "^(" + k_patrones_hora + ")$";
            texto = texto.trim();
            ok.es = texto.matches(regex);
            if (ok.es == false) {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in(in, "Error, no cumple con alguno de los formatos: ") + "(hh:MM | hh:MM:ss)");
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Valida una url con el formatos k_patrones_web_url
     * @param texto
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public static boolean validar_url_de_navegador(String texto, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            ResourceBundle in;
            String regex = "^(" + k_patrones_web_url + ")$";
            texto = texto.trim();
            ok.es = texto.matches(regex);
            if (ok.es == false) {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in(in, "Error, no cumple con alguno de los formatos: ") + "(hh:MM | hh:MM:ss)");
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }    
    /**
     * Valida una url con el formatos k_patrones_web_url_con_protocolo
     * @param texto
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public static boolean validar_url_con_protocolo(String texto, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            ResourceBundle in;
            String regex = "^(" + k_patrones_web_url_con_protocolo + ")$";
            texto = texto.trim();
            ok.es = texto.matches(regex);
            if (ok.es == false) {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in(in, "Error, no cumple con alguno de los formatos: ") + "(hh:MM | hh:MM:ss)");
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Valida una url con el formatos k_patrones_email
     * @param texto
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public static boolean validar_email(String texto, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            ResourceBundle in;
            String regex = "^(" + k_patrones_email + ")$";
            texto = texto.trim();
            ok.es = texto.matches(regex);
            if (ok.es == false) {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in(in, "Error, no cumple con el formato: ") + tr.in(in, "email"));
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Valida una url con el formatos k_patrones_telefono
     * @param texto
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public static boolean validar_telefono(String texto, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            ResourceBundle in;
            String regex = "^(" + k_patrones_telefono + ")$";
            texto = texto.trim();
            ok.es = texto.matches(regex);
            if (ok.es == false) {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in(in, "Error, no cumple con el formato: ") + tr.in(in, "teléfono"));
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Valida un entero con el formatos k_patrones_telefono
     * @param texto
     * @param es_agrupador_permitido
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public static boolean validar_numero(String texto, boolean es_agrupador_permitido, oks ok, Object ... extras_array) throws Exception {
        convertir_numero(texto, es_agrupador_permitido, ok, extras_array);
        return ok.es;
    }
    /**
     * Convertir un texto a un número entero con el formatos k_patrones_telefono
     * @param texto
     * @param es_agrupador_permitido
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public static Long convertir_numero(String texto, boolean es_agrupador_permitido, oks ok, Object ... extras_array) throws Exception {
        Long num_largo = null;
        ResourceBundle in;
        try {
            if (ok.es == false) { return null; }
            NumberFormat numberFormat = NumberFormat.getInstance();
            ParsePosition parsePosition = new ParsePosition(0);
            numberFormat.setGroupingUsed(false);
            Number number = numberFormat.parse(texto, parsePosition);
            if (parsePosition.getErrorIndex() != -1
             || parsePosition.getIndex() != texto.length()) {
                in = ResourceBundles.getBundle(k_in_ruta);
                if (es_agrupador_permitido) {
                    ok.setTxt(tr.in(in, "Error, no es un entero decimal válido "));
                } else {
                    ok.setTxt(tr.in(in, "Error, no es un entero decimal válido (no use agrupador de dígitos) "));
                }
            }
            num_largo = number.longValue();
        } catch (Exception e) {
            in = ResourceBundles.getBundle(k_in_ruta);
            if (es_agrupador_permitido) {
                ok.setTxt(tr.in(in, "Error, no es un entero decimal válido "));
            } else {
                ok.setTxt(tr.in(in, "Error, no es un entero decimal válido (no use agrupador de dígitos) "));
            }
        }
        return num_largo;
    }
    /**
     * Valida un decimal con el formatos k_patrones_telefono
     * @param texto
     * @param es_agrupador_permitido
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public static boolean validar_decimal(String texto, boolean es_agrupador_permitido, oks ok, Object ... extras_array) throws Exception {
        convertir_decimal(texto, es_agrupador_permitido, ok, extras_array);
        return ok.es;
    }
    /**
     * Convertir un texto a un número decimal con el formatos k_patrones_telefono
     * @param texto
     * @param es_agrupador_permitido
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public static Double convertir_decimal(String texto, boolean es_agrupador_permitido, oks ok, Object ... extras_array) throws Exception {
        Double doble = null;
        ResourceBundle in;
        try {
            if (ok.es == false) { return null; }
            NumberFormat decimalFormat = DecimalFormat.getInstance();
            ParsePosition parsePosition = new ParsePosition(0);
            decimalFormat.setGroupingUsed(es_agrupador_permitido);
            Number number = decimalFormat.parse(texto, parsePosition);
            if (parsePosition.getErrorIndex() != -1
             || parsePosition.getIndex() != texto.length()) {
                in = ResourceBundles.getBundle(k_in_ruta);
                if (es_agrupador_permitido) {
                    ok.setTxt(tr.in(in, "Error, no es un número decimal válido "));
                } else {
                    ok.setTxt(tr.in(in, "Error, no es un número decimal válido (no use agrupador de dígitos) "));
                }
            }
            doble = number.doubleValue();
        } catch (Exception e) {
            in = ResourceBundles.getBundle(k_in_ruta);
            if (es_agrupador_permitido) {
                ok.setTxt(tr.in(in, "Error, no es un número decimal válido "));
            } else {
                ok.setTxt(tr.in(in, "Error, no es un número decimal válido (no use agrupador de dígitos) "));
            }
        }
        return doble;
    }
    /**
     * Valida un decimal con el formatos k_patrones_telefono
     * @param texto
     * @param es_agrupador_permitido
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public static boolean validar_decimal_us(String texto, boolean es_agrupador_permitido, oks ok, Object ... extras_array) throws Exception {
        convertir_decimal_us(texto, es_agrupador_permitido, ok, extras_array);
        return ok.es;
    }
    /**
     * Convertir un texto a un número decimal con el formatos k_patrones_telefono
     * @param texto
     * @param es_agrupador_permitido
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public static Double convertir_decimal_us(String texto, boolean es_agrupador_permitido, oks ok, Object ... extras_array) throws Exception {
        Double doble = null;
        ResourceBundle in;
        try {
            if (ok.es == false) { return null; }
            NumberFormat decimalFormat = DecimalFormat.getInstance(Locale.US);
            ParsePosition parsePosition = new ParsePosition(0);
            decimalFormat.setGroupingUsed(es_agrupador_permitido);
            Number number = decimalFormat.parse(texto, parsePosition);
            if (parsePosition.getErrorIndex() != -1
             || parsePosition.getIndex() != texto.length()) {
                in = ResourceBundles.getBundle(k_in_ruta);
                if (es_agrupador_permitido) {
                    ok.setTxt(tr.in(in, "Error, no es un número decimal válido "));
                } else {
                    ok.setTxt(tr.in(in, "Error, no es un número decimal válido (no use agrupador de dígitos) "));
                }
            }
            doble = number.doubleValue();
        } catch (Exception e) {
            in = ResourceBundles.getBundle(k_in_ruta);
            if (es_agrupador_permitido) {
                ok.setTxt(tr.in(in, "Error, no es un número decimal válido "));
            } else {
                ok.setTxt(tr.in(in, "Error, no es un número decimal válido (no use agrupador de dígitos) "));
            }
        }
        return doble;
    }
    /**
     * Valida una url con el formatos k_patrones_email
     * @param texto
     * @param regex
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public static boolean validar_patron(String texto, String regex, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            ResourceBundle in;
            texto = texto.trim();
            ok.es = texto.matches(regex);
            if (ok.es == false) {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in(in, "Error, no cumple con la expresión regular: ") + regex);
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Convierte una fecha según los formatos de k_patrones_formato_fecha (separados por |)
     * @param texto
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public static Date convertir_fecha(String texto, oks ok, Object ... extras_array) throws Exception {
        Date date = null;
        try {
            if (ok.es == false) { return null; }
            String [] formatos_array = k_patrones_formato_fecha.split("\\|");
            SimpleDateFormat dateFormat;
            ParsePosition parsePosition;
            for (String formato: formatos_array) {
                formato = formato.trim();
                dateFormat = new SimpleDateFormat(formato);
                parsePosition = new ParsePosition(0);
                try {
                    date = dateFormat.parse(texto, parsePosition);
                    if (parsePosition.getErrorIndex() == -1
                     && parsePosition.getIndex() == texto.length()) {
                        break;
                    }
                } catch (Exception e_ignorar) {}
            }
        } catch (Exception e) {
            throw e;
        }
        return date;
    }    
    /**
     * Convierte una hora según los formatos de k_patrones_formato_hora (separados por |)
     * @param texto
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public static Date convertir_hora(String texto, oks ok, Object ... extras_array) throws Exception {
        Date date = null;
        try {
            if (ok.es == false) { return null; }
            String [] formatos_array = k_patrones_formato_hora.split("\\|");
            SimpleDateFormat dateFormat;
            ParsePosition parsePosition;
            for (String formato: formatos_array) {
                formato = formato.trim();
                dateFormat = new SimpleDateFormat(formato);
                parsePosition = new ParsePosition(0);
                try {
                    date = dateFormat.parse(texto, parsePosition);
                    if (parsePosition.getErrorIndex() == -1
                     && parsePosition.getIndex() == texto.length()) {
                        break;
                    }
                } catch (Exception e_ignorar) {}
            }
        } catch (Exception e) {
            throw e;
        }
        return date;
    }
    
    /**
     * Convierte una hora según los formatos de k_patrones_formato_fecha y k_patrones_formato_hora (separados por |)
     * El texto se separa por el espacio en blanco entre la fecha y la hora. Y se convierten por separado
     * @param texto
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public static Date convertir_fecha_y_hora(String texto, oks ok, Object ... extras_array) throws Exception {
        Date date = null;
        try {
            if (ok.es == false) { return null; }
            ResourceBundle in;
            String [] textos_array = texto.split("[Tt\\s]");
            if (textos_array.length != 2) { return null; }
            Date date_fecha;
            date_fecha = convertir_fecha(textos_array[0], ok);
            if (date_fecha == null) { return null; }
            Date date_hora;
            date_hora = convertir_hora(textos_array[1], ok);
            if (date_hora == null) { return null; }
            Long time;
            time = date_fecha.getTime();
            time = time + date_hora.getTime();
            date = new Date(time);
        } catch (Exception e) {
            throw e;
        }
        return date;
    }
    
}
