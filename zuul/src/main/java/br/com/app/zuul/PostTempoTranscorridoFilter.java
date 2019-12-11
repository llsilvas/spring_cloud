package br.com.app.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component
public class PostTempoTranscorridoFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(PostTempoTranscorridoFilter.class);


    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        logger.info(String.format("Apos post"));

        Long tempoInicio = (Long) request.getAttribute("tempoInicio");
        Long tempoFinal =  System.currentTimeMillis();

        Long tempoTrans = tempoFinal - tempoInicio;
       logger.info(String.format("Tempo trans %s",  tempoTrans.doubleValue()/1000.00));
        logger.info(String.format("Tempo trans milesegundos %s",  tempoTrans));

        return null;
    }
}
