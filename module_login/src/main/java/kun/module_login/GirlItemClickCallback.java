
package kun.module_login;


import kun.bdbd.coremodel.datamodel.http.entities.GirlsData;

public interface GirlItemClickCallback {
    void onClick(GirlsData.ResultsBean girlsItem);
}
