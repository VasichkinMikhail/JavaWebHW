package ru.geekbrains;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {AppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        // РЎРѕР·РґР°РЅРёРµ С„РёР»СЊС‚СЂР° РєРѕРґРёСЂРѕРІРєРё, РєРѕС‚РѕСЂС‹Р№ РїРѕР·РІРѕР»РёС‚ СЂР°Р±РѕС‚Р°С‚СЊ СЃ СЂСѓСЃСЃРєРёРјРё
        // СЃРёРјРІРѕР»Р°РјРё
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        // РЎРѕР·РґР°РЅРёРµ С„РёР»СЊС‚СЂР°, РєРѕС‚РѕСЂС‹Р№ РґРѕР±Р°РІР»СЏРµС‚ РїРѕРґРґРµСЂР¶РєСѓ HTTP-РјРµС‚РѕРґРѕРІ (РЅР°РїСЂРёРјРµСЂ
        // С‚Р°РєРёС…, РєР°Рє PUT), РЅРµРѕР±С…РѕРґРёРјС‹С… РґР»СЏ REST API
        HiddenHttpMethodFilter httpMethodFilter = new HiddenHttpMethodFilter();
        return new Filter[]{characterEncodingFilter, httpMethodFilter};
    }
}

