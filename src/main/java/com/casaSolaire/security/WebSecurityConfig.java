package com.casaSolaire.security;

import com.casaSolaire.security.jwt.JwtAuthEntryPoint;
import com.casaSolaire.security.jwt.JwtAuthTokenFilter;
import com.casaSolaire.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200");

            }
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/**/auth/signUp").permitAll()
                .antMatchers("/**/auth/registerUser").permitAll()
                .antMatchers("/**/auth/authenticated").permitAll()
                .antMatchers("/**/articles/create").permitAll()
                .antMatchers("/**/articles/createWithFile").permitAll()
                .antMatchers("/**/articles/searchArticleByKeyword").permitAll()
                .antMatchers("/**/articles/searchArticleByPrice/**").permitAll()
                .antMatchers("/**/articles/all").permitAll()
                .antMatchers("/**/articles/**").permitAll()
                .antMatchers("/**/articles/searchArticleByselectedIsTrue").permitAll()
                .antMatchers("/**/articles/searchTop12ArticleOrderByCreatedDateDesc").permitAll()
                .antMatchers("/**/articles/searchArticleByKeyword").permitAll()
                .antMatchers("/**/articles/articlesByScategories").permitAll()
                .antMatchers("/**/articles/searchArticleByScategoryByPageables").permitAll()
                .antMatchers("/**/articles/searchArticleBySamePriceByPageables").permitAll()
                .antMatchers("/**/articles/searchArticleByPrice/**").permitAll()
                .antMatchers("/**/articles/searchbyReference/**").permitAll()
                .antMatchers("/**/articles/photoArticle/{idArticle}").permitAll()
                .antMatchers("/**/articles/uploadArticlePhoto/{id}").permitAll()
                .antMatchers("/**/articles/countNumberOfProductInSubCat/{subCatId}").permitAll()
                .antMatchers("/**/categories/**").permitAll()
                .antMatchers("/**/categories/all").permitAll()
                .antMatchers("/**/categories/searchAllCategoriesOrderByIdDesc").permitAll()
                .antMatchers("/**/scategories/**").permitAll()
                .antMatchers("/**/scategories/all").permitAll()
                .antMatchers("/**/scategories/searchAllSubCategoriesOrderByIdDesc").permitAll()
                .antMatchers("/**/scategories/findById/{idScategory}").permitAll()
                .antMatchers("/**/scategories/update/{idScategory}").permitAll()
                .antMatchers("/**/fournisseurs/create").permitAll()
                .antMatchers("/**/fournisseurs/findById/{idFournisseur}").permitAll()
                .antMatchers("/**/fournisseurs/update/{idFournisseur}").permitAll()
                .antMatchers("/**/fournisseurs/**").permitAll()
                .antMatchers("/**/fournisseurs/searchAllFournisseursOrderByIdDesc").permitAll()
                .antMatchers("/**/commandes/create").permitAll()
                .antMatchers("/**/commandes/all").permitAll()
                .antMatchers("/**/commandes/countNumberOfCommande").permitAll()
                .antMatchers("/**/commandes/sumTotalOfCommandeByDay").permitAll()
                .antMatchers("/**/commandes/sumTotalOfCommandeByMonth").permitAll()
                .antMatchers("/**/commandes/sumTotalOfCommandeByYear").permitAll()
                .antMatchers("/**/commandes/numberOfCommandeByMonth").permitAll()

                .antMatchers("/**/commandes/countNumberOfOrdersInMonth").permitAll()
                .antMatchers("/**/commandes/countNumberOfOrdersByPendingStatus").permitAll()
                .antMatchers("/**/commandes/searchCommandeByUtilisateurByPageables/***").permitAll()

                .antMatchers("/**/commandes/searchCommandeByCustomerByPageables/***").permitAll()
                .antMatchers("/**/commandes/searchCommandeByUser/*").permitAll()

                .antMatchers("/**/commandes/sumTotaleOfCommandeByMonthByList").permitAll()
                .antMatchers("/**/commandes/sumTotaleOfCommandeByYearList").permitAll()

                .antMatchers("/**/commandes/searchAllComandesOrderByIdDesc").permitAll()
                .antMatchers("/**/commandes/findListOrderByStatuePending").permitAll()
                .antMatchers("/**/commandes/findListOrderByStatuePayed").permitAll()

                .antMatchers("/**/commandes/searchCommandeByUserIdOrderByIdDesc/*").permitAll()
                .antMatchers("/**/commandes/searchCommandeByBillingAddressIdDesc/*").permitAll()
                .antMatchers("/**/commandes/searchCommandeByShippingAddressIdDesc/*").permitAll()

                .antMatchers("/**/commandes/searchCommandesByUtilisateurIdByPageables/***").permitAll()

                .antMatchers("/**/commandes/updateStatusOfCommande/*").permitAll()

                .antMatchers("/**/checkout/placeToOrder").permitAll()

                .antMatchers("/**/checkout/placeToOrderWithUser/**").permitAll()

                .antMatchers("/**/checkout/purchase").permitAll()

                .antMatchers("/**/countries/create").permitAll()
                .antMatchers("/**/countries/findById/{idCountry}").permitAll()
                .antMatchers("/**/countries/all").permitAll()
                .antMatchers("/**/countries/**").permitAll()
                .antMatchers("/**/countries/delete/{idCountry}").permitAll()
                .antMatchers("/**/countries/searchAllCountryOrderByIdDesc").permitAll()
                .antMatchers("/**/countries/countNumberOfCountries").permitAll()
                .antMatchers("/**/fournisseurs/all").permitAll()
                .antMatchers("/**/fournisseurs/countNumberOfFournisseurs").permitAll()

                .antMatchers("/**/lignecommandes/all").permitAll()
                .antMatchers("/**/lignecommandes/searchAllLigneCommandeOrderByIdDesc").permitAll()
                .antMatchers("/**/lignecommandes/findListArticleGroupByIdDesc").permitAll()
                .antMatchers("/**/lignecommandes/searchAllLigneCommandesByCommandeId/*").permitAll()
                .antMatchers("/**/lignecommandes/searchTopLigneCommandesOrderByIdDesc").permitAll()
                .antMatchers("/**/lignecommandes/findListArticleGroupByIdDesc").permitAll()
                .antMatchers("/**/states/**").permitAll()
                .antMatchers("/**/states/create").permitAll()
                .antMatchers("/**/states/findById/*").permitAll()
                .antMatchers("/**/states/update/{idState}").permitAll()
                .antMatchers("/**/states/all").permitAll()
                .antMatchers("/**/states/searchAllStatesOrderByIdDesc").permitAll()
                .antMatchers("/**/states/searchStateByCountryCode/**").permitAll()
                .antMatchers("/**/utilisateurs/all").permitAll()
                .antMatchers("/**/utilisateurs/findById/{idUtilisateur}").permitAll()
                .antMatchers("/**/utilisateurs/update/{idUser}").permitAll()
                .antMatchers("/**/utilisateurs/avatar/{id}").permitAll()
                .antMatchers("/**/utilisateurs/uploadUserPhoto/{id}").permitAll()
                .antMatchers("/**/utilisateurs/*").permitAll()
                .antMatchers("/**/utilisateurs/updateCustomerProfileByUsername").permitAll()
                .antMatchers("/**/clients/**").permitAll()
                .antMatchers("/**/clients/all").permitAll()
                .antMatchers("/**/clients/searchAllClientsOrderByIdDesc").permitAll()
                .antMatchers("/**/clients/countNumberOfClient").permitAll()
                .antMatchers("/**/notifications/**").permitAll()
                .antMatchers("/**/notifications/all").permitAll()
                .antMatchers("/**/notifications/countNumberOfNotification").permitAll()
                .antMatchers("/**/notifications/createRatingToArticle/**").permitAll()
                .antMatchers("/**/notifications/searchTop3RatingOrderByCreatedDateDesc").permitAll()

                .antMatchers("/**/notifications/countNumberOfNotificationByProductId/{idProd}").permitAll()
                .antMatchers("/**/notifications/searchTop4RatingOrderByCreatedDateDescByProductId/{idProd}").permitAll()


                .antMatchers("/**/addresses/all").permitAll()
                .antMatchers("/**/addresses/findById/{idAddress}").permitAll()
                .antMatchers("/**/addresses/searchAllAddressOrderByIdDesc").permitAll()
                .antMatchers("/**/addresses/**").permitAll()


                .antMatchers("/**/addresslivraisons/all").permitAll()
                .antMatchers("/**/addresslivraisons/searchAllAddressLivraisonsOrderByIdDesc").permitAll()

                .antMatchers("/**/addresseclients/searchAllAddressClientsOrderByIdDesc").permitAll()


                .antMatchers("/**/newsletters/create").permitAll()
                .antMatchers("/**/newsletters/findById/{idNewsletter}").permitAll()
                .antMatchers("/**/newsletters/countNumberOfNewsletters").permitAll()
                .antMatchers("/**/newsletters/searchAllNewslettersOrderByIdDesc").permitAll()
                .antMatchers("/**/newsletters/delete/{idNewsletter}").permitAll()


                .antMatchers("/**/emails/all").permitAll()
                .antMatchers("/**/emails/findById/*").permitAll()
                .antMatchers("/**/emails/searchAllEmailssOrderByIdDesc").permitAll()

                .antMatchers("/**/emails/sendEmail").permitAll()
                .antMatchers("/**/emails/sendToFournisseur").permitAll()
                .antMatchers("/**/emails/sendToNewsletter").permitAll()
                .antMatchers("/**/emails/sendMailToAllCustomers").permitAll()
                .antMatchers("/**/emails/sendMailToManager").permitAll()
                .antMatchers("/**/emails/findById/{idEmail}").permitAll()
                .antMatchers("/**/emails/searchAllEmailsOrderByIdDesc").permitAll()
                .antMatchers("/**/emails/countNumberOfEmail").permitAll()
                .antMatchers("/**/emails/delete/{idEmail}").permitAll()
                .antMatchers("/**/historiqueLogins/searchAllHistoriqueLoginsOrderByIdDesc").permitAll()

                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }


}
