package team.left.framework.context.core.classloader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import team.left.framework.context.core.util.ContextClassDecision;
import team.left.framework.context.exception.BeanLoaderException;

public class DefaultBeanClassLoader implements BeanClassLoader {
    private final ContextClassDecision contextClassDecision;

    public DefaultBeanClassLoader(ContextClassDecision contextClassDecision) {
        this.contextClassDecision = contextClassDecision;
    }

    @Override
    public Set<Class<?>> findAllOnBasePackage(String basePackage) throws BeanLoaderException {
        final String basePackagePath = basePackage.replaceAll("[.]", "/");
        Set<Class<?>> beanClasses = new HashSet<>();
        searchByDfs(basePackagePath, beanClasses);
        return beanClasses;
    }

    private void searchByDfs(String path, Set<Class<?>> beanClasses) {
        URL url = getClass().getClassLoader().getResource(path);
        File file;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            throw new BeanLoaderException("Bean 로딩 중 에러 발생: " + path, e);
        }

        if (!file.exists()) {
            return;
        }

        if (file.isDirectory()) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
                br.lines().forEach((nextFile) ->
                        searchByDfs(path + "/" + nextFile, beanClasses));
                return;
            } catch (IOException e) {
                throw new BeanLoaderException("Bean 로딩 중 에러 발생: " + path, e);
            }
        }

        final String filename = file.getName();
        if (isFileAClass(filename)) {
            String fqcn = path.replaceAll("/", ".").substring(0, path.lastIndexOf("."));
            try {
                Class<?> clazz = Class.forName(fqcn);
                if (this.contextClassDecision.isBeanToBeIncludedInContext(clazz)) {
                    beanClasses.add(clazz);
                }
            } catch (ClassNotFoundException e) {
                throw new BeanLoaderException("Bean 로딩 중 에러 발생: " + path, e);
            }
            
        }
    }

    private boolean isFileAClass(String filename) {
        return filename.endsWith(".class");
    }
}
