//package com.louis.mybatis.dynamic.base;
//
//import org.apache.ibatis.annotations.*;
//
//
/// **
// * @author louis
// * <p>
// * Date: 2019/9/23
// * Description:
// */
//public interface BaseMapper<T,K> {
//    @InsertProvider(type = ProviderSourceBuilder.class, method = "build")
//     Long insert(T model);
//
//    @UpdateProvider(type = ProviderSourceBuilder.class, method = "build")
//     Long updateById(T model);
//
//    @DeleteProvider(type = ProviderSourceBuilder.class, method = "build")
//     Long deleteById(@Param("id") K id);
//
//    @SelectProvider(type = ProviderSourceBuilder.class, method = "build")
//     T getById(@Param("id") K id);
//
//    @SelectProvider(type = ProviderSourceBuilder.class, method = "build")
//     Boolean existById(@Param("id") K id);
//}
