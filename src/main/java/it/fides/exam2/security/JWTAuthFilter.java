package it.fides.exam2.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import it.fides.exam2.exceptions.UnauthorizedException;
import it.fides.exam2.models.entities.UtenteEntity;
import it.fides.exam2.services.UtenteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {
	@Autowired
	private JWTTools jwtTools;
	@Autowired
	private UtenteService usersService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = getJwtFromRequest(request);
        if (token == null) {
			throw new UnauthorizedException("Per favore passa il Bearer Token nell'Authorization header");
		} else {
			//token = authHeader.substring(7);
			System.out.println("TOKEN -> " + token);
			jwtTools.verifyToken(token);
			String id = jwtTools.extractIdFromToken(token);
			UtenteEntity currentUtente = usersService.getUtenteById(Long.parseLong(id));

			Authentication authentication = new UsernamePasswordAuthenticationToken(currentUtente, null,
					currentUtente.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);

			filterChain.doFilter(request, response);
		}

	}
	
	private String getJwtFromRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return new AntPathMatcher().match("/auth/**", request.getServletPath());
	}
}
