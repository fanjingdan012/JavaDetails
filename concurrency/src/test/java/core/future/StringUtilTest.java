package core.future;

import junit.framework.TestCase;
import org.junit.Test;

import static core.future.StringUtil.camelName;
import static core.future.StringUtil.underscoreName;

public class StringUtilTest extends TestCase {
    @Test
    public void testUnderscoreName(){
        System.out.println(underscoreName("OrganizationMapViewOwn"));
        System.out.println(underscoreName("OrganizationMapViewAll"));
        System.out.println(underscoreName("OrganizationMapCreate"));
        System.out.println(underscoreName("OrganizationMapModifyOwn"));
        System.out.println(underscoreName("OrganizationMapModifyAll"));
        System.out.println(underscoreName("ProcessCatalogViewOwn"));
        System.out.println(underscoreName("ProcessCatalogViewAll"));
        System.out.println(underscoreName("ProcessCatalogCreate"));
        System.out.println(underscoreName("ProcessCatalogModifyOwn"));
        System.out.println(underscoreName("ProcessCatalogModifyAll"));
        System.out.println(underscoreName("RepositoryCreate"));
        System.out.println(underscoreName("RepositoryView"));
        System.out.println(underscoreName("RepositoryModify"));
        System.out.println(underscoreName("OrganizationMapActiveOwn"));
        System.out.println(underscoreName("OrganizationMapRetireOwn"));
        System.out.println(underscoreName("OrganizationMapActiveAll"));
        System.out.println(underscoreName("OrganizationMapRetireAll"));
        System.out.println(underscoreName("OrganizationMapViewAll"));
        System.out.println(underscoreName("OrganizationMapViewOwn"));
        System.out.println(underscoreName("ProcessCatalogActivateOwn"));
        System.out.println(underscoreName("ProcessCatalogRetireOwn"));
        System.out.println(underscoreName("ProcessCatalogActivateAll"));
        System.out.println(underscoreName("ProcessCatalogRetireAll"));
        System.out.println(underscoreName("ProcessCatalogViewAll"));
        System.out.println(underscoreName("ProcessCatalogViewOwn"));
        System.out.println(underscoreName("RepositoryView"));
        System.out.println(underscoreName("OrganizationMapViewOwn"));
        System.out.println(underscoreName("OrganizationMapViewAll"));
        System.out.println(underscoreName("ProcessCatalogViewOwn"));
        System.out.println(underscoreName("ProcessCatalogViewAll"));
        System.out.println(underscoreName("RepositoryView"));
    }

    @Test
    public void testCamelName(){
        System.out.println(camelName("a_bc"));
    }

}