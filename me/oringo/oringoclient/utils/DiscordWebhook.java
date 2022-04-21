/*     */ package me.oringo.oringoclient.utils;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.lang.reflect.Array;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.net.ssl.HttpsURLConnection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DiscordWebhook
/*     */ {
/*     */   private final String url;
/*     */   private String content;
/*     */   private String username;
/*     */   private String avatarUrl;
/*     */   private boolean tts;
/*  25 */   private List<EmbedObject> embeds = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DiscordWebhook(String url) {
/*  33 */     this.url = url;
/*     */   }
/*     */   
/*     */   public void setContent(String content) {
/*  37 */     this.content = content;
/*     */   }
/*     */   
/*     */   public void setUsername(String username) {
/*  41 */     this.username = username;
/*     */   }
/*     */   
/*     */   public void setAvatarUrl(String avatarUrl) {
/*  45 */     this.avatarUrl = avatarUrl;
/*     */   }
/*     */   
/*     */   public void setTts(boolean tts) {
/*  49 */     this.tts = tts;
/*     */   }
/*     */   
/*     */   public void addEmbed(EmbedObject embed) {
/*  53 */     this.embeds.add(embed);
/*     */   }
/*     */   
/*     */   public void execute() throws IOException {
/*  57 */     if (this.content == null && this.embeds.isEmpty()) {
/*  58 */       throw new IllegalArgumentException("Set content or add at least one EmbedObject");
/*     */     }
/*     */     
/*  61 */     JSONObject json = new JSONObject();
/*     */     
/*  63 */     json.put("content", this.content);
/*  64 */     json.put("username", this.username);
/*  65 */     json.put("avatar_url", this.avatarUrl);
/*  66 */     json.put("tts", Boolean.valueOf(this.tts));
/*     */     
/*  68 */     if (!this.embeds.isEmpty()) {
/*  69 */       List<JSONObject> embedObjects = new ArrayList<>();
/*     */       
/*  71 */       for (EmbedObject embed : this.embeds) {
/*  72 */         JSONObject jsonEmbed = new JSONObject();
/*     */         
/*  74 */         jsonEmbed.put("title", embed.getTitle());
/*  75 */         jsonEmbed.put("description", embed.getDescription());
/*  76 */         jsonEmbed.put("url", embed.getUrl());
/*     */         
/*  78 */         if (embed.getColor() != null) {
/*  79 */           Color color = embed.getColor();
/*  80 */           int rgb = color.getRed();
/*  81 */           rgb = (rgb << 8) + color.getGreen();
/*  82 */           rgb = (rgb << 8) + color.getBlue();
/*     */           
/*  84 */           jsonEmbed.put("color", Integer.valueOf(rgb));
/*     */         } 
/*     */         
/*  87 */         EmbedObject.Footer footer = embed.getFooter();
/*  88 */         EmbedObject.Image image = embed.getImage();
/*  89 */         EmbedObject.Thumbnail thumbnail = embed.getThumbnail();
/*  90 */         EmbedObject.Author author = embed.getAuthor();
/*  91 */         List<EmbedObject.Field> fields = embed.getFields();
/*     */         
/*  93 */         if (footer != null) {
/*  94 */           JSONObject jsonFooter = new JSONObject();
/*     */           
/*  96 */           jsonFooter.put("text", footer.getText());
/*  97 */           jsonFooter.put("icon_url", footer.getIconUrl());
/*  98 */           jsonEmbed.put("footer", jsonFooter);
/*     */         } 
/*     */         
/* 101 */         if (image != null) {
/* 102 */           JSONObject jsonImage = new JSONObject();
/*     */           
/* 104 */           jsonImage.put("url", image.getUrl());
/* 105 */           jsonEmbed.put("image", jsonImage);
/*     */         } 
/*     */         
/* 108 */         if (thumbnail != null) {
/* 109 */           JSONObject jsonThumbnail = new JSONObject();
/*     */           
/* 111 */           jsonThumbnail.put("url", thumbnail.getUrl());
/* 112 */           jsonEmbed.put("thumbnail", jsonThumbnail);
/*     */         } 
/*     */         
/* 115 */         if (author != null) {
/* 116 */           JSONObject jsonAuthor = new JSONObject();
/*     */           
/* 118 */           jsonAuthor.put("name", author.getName());
/* 119 */           jsonAuthor.put("url", author.getUrl());
/* 120 */           jsonAuthor.put("icon_url", author.getIconUrl());
/* 121 */           jsonEmbed.put("author", jsonAuthor);
/*     */         } 
/*     */         
/* 124 */         List<JSONObject> jsonFields = new ArrayList<>();
/* 125 */         for (EmbedObject.Field field : fields) {
/* 126 */           JSONObject jsonField = new JSONObject();
/*     */           
/* 128 */           jsonField.put("name", field.getName());
/* 129 */           jsonField.put("value", field.getValue());
/* 130 */           jsonField.put("inline", Boolean.valueOf(field.isInline()));
/*     */           
/* 132 */           jsonFields.add(jsonField);
/*     */         } 
/*     */         
/* 135 */         jsonEmbed.put("fields", jsonFields.toArray());
/* 136 */         embedObjects.add(jsonEmbed);
/*     */       } 
/*     */       
/* 139 */       json.put("embeds", embedObjects.toArray());
/*     */     } 
/*     */     
/* 142 */     URL url = new URL(this.url);
/* 143 */     HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
/* 144 */     connection.setConnectTimeout(150000);
/* 145 */     connection.addRequestProperty("Content-Type", "application/json");
/* 146 */     connection.addRequestProperty("User-Agent", "Java-DiscordWebhook-BY-Gelox_");
/* 147 */     connection.setDoOutput(true);
/* 148 */     connection.setRequestMethod("POST");
/*     */     
/* 150 */     OutputStream stream = connection.getOutputStream();
/* 151 */     stream.write(json.toString().getBytes());
/* 152 */     stream.flush();
/* 153 */     stream.close();
/*     */     
/* 155 */     connection.getInputStream().close();
/* 156 */     connection.disconnect();
/*     */   }
/*     */   
/*     */   public static class EmbedObject
/*     */   {
/*     */     private String title;
/*     */     private String description;
/*     */     private String url;
/*     */     private Color color;
/*     */     private Footer footer;
/*     */     private Thumbnail thumbnail;
/*     */     private Image image;
/*     */     private Author author;
/* 169 */     private List<Field> fields = new ArrayList<>();
/*     */     
/*     */     public String getTitle() {
/* 172 */       return this.title;
/*     */     }
/*     */     
/*     */     public String getDescription() {
/* 176 */       return this.description;
/*     */     }
/*     */     
/*     */     public String getUrl() {
/* 180 */       return this.url;
/*     */     }
/*     */     
/*     */     public Color getColor() {
/* 184 */       return this.color;
/*     */     }
/*     */     
/*     */     public Footer getFooter() {
/* 188 */       return this.footer;
/*     */     }
/*     */     
/*     */     public Thumbnail getThumbnail() {
/* 192 */       return this.thumbnail;
/*     */     }
/*     */     
/*     */     public Image getImage() {
/* 196 */       return this.image;
/*     */     }
/*     */     
/*     */     public Author getAuthor() {
/* 200 */       return this.author;
/*     */     }
/*     */     
/*     */     public List<Field> getFields() {
/* 204 */       return this.fields;
/*     */     }
/*     */     
/*     */     public EmbedObject setTitle(String title) {
/* 208 */       this.title = title;
/* 209 */       return this;
/*     */     }
/*     */     
/*     */     public EmbedObject setDescription(String description) {
/* 213 */       this.description = description;
/* 214 */       return this;
/*     */     }
/*     */     
/*     */     public EmbedObject setUrl(String url) {
/* 218 */       this.url = url;
/* 219 */       return this;
/*     */     }
/*     */     
/*     */     public EmbedObject setColor(Color color) {
/* 223 */       this.color = color;
/* 224 */       return this;
/*     */     }
/*     */     
/*     */     public EmbedObject setFooter(String text, String icon) {
/* 228 */       this.footer = new Footer(text, icon);
/* 229 */       return this;
/*     */     }
/*     */     
/*     */     public EmbedObject setThumbnail(String url) {
/* 233 */       this.thumbnail = new Thumbnail(url);
/* 234 */       return this;
/*     */     }
/*     */     
/*     */     public EmbedObject setImage(String url) {
/* 238 */       this.image = new Image(url);
/* 239 */       return this;
/*     */     }
/*     */     
/*     */     public EmbedObject setAuthor(String name, String url, String icon) {
/* 243 */       this.author = new Author(name, url, icon);
/* 244 */       return this;
/*     */     }
/*     */     
/*     */     public EmbedObject addField(String name, String value, boolean inline) {
/* 248 */       this.fields.add(new Field(name, value, inline));
/* 249 */       return this;
/*     */     }
/*     */     
/*     */     private class Footer {
/*     */       private String text;
/*     */       private String iconUrl;
/*     */       
/*     */       private Footer(String text, String iconUrl) {
/* 257 */         this.text = text;
/* 258 */         this.iconUrl = iconUrl;
/*     */       }
/*     */       
/*     */       private String getText() {
/* 262 */         return this.text;
/*     */       }
/*     */       
/*     */       private String getIconUrl() {
/* 266 */         return this.iconUrl;
/*     */       }
/*     */     }
/*     */     
/*     */     private class Thumbnail {
/*     */       private String url;
/*     */       
/*     */       private Thumbnail(String url) {
/* 274 */         this.url = url;
/*     */       }
/*     */       
/*     */       private String getUrl() {
/* 278 */         return this.url;
/*     */       }
/*     */     }
/*     */     
/*     */     private class Image {
/*     */       private String url;
/*     */       
/*     */       private Image(String url) {
/* 286 */         this.url = url;
/*     */       }
/*     */       
/*     */       private String getUrl() {
/* 290 */         return this.url;
/*     */       }
/*     */     }
/*     */     
/*     */     private class Author {
/*     */       private String name;
/*     */       private String url;
/*     */       private String iconUrl;
/*     */       
/*     */       private Author(String name, String url, String iconUrl) {
/* 300 */         this.name = name;
/* 301 */         this.url = url;
/* 302 */         this.iconUrl = iconUrl;
/*     */       }
/*     */       
/*     */       private String getName() {
/* 306 */         return this.name;
/*     */       }
/*     */       
/*     */       private String getUrl() {
/* 310 */         return this.url;
/*     */       }
/*     */       
/*     */       private String getIconUrl() {
/* 314 */         return this.iconUrl;
/*     */       }
/*     */     }
/*     */     
/*     */     private class Field {
/*     */       private String name;
/*     */       private String value;
/*     */       private boolean inline;
/*     */       
/*     */       private Field(String name, String value, boolean inline) {
/* 324 */         this.name = name;
/* 325 */         this.value = value;
/* 326 */         this.inline = inline;
/*     */       }
/*     */       
/*     */       private String getName() {
/* 330 */         return this.name;
/*     */       }
/*     */       
/*     */       private String getValue() {
/* 334 */         return this.value;
/*     */       }
/*     */       
/*     */       private boolean isInline() {
/* 338 */         return this.inline; } } } private class Footer { private String text; private String iconUrl; private Footer(String text, String iconUrl) { this.text = text; this.iconUrl = iconUrl; } private String getText() { return this.text; } private String getIconUrl() { return this.iconUrl; } } private class Thumbnail { private String url; private Thumbnail(String url) { this.url = url; } private String getUrl() { return this.url; } } private class Image { private String url; private Image(String url) { this.url = url; } private String getUrl() { return this.url; } } private class Author { private String name; private String url; private String iconUrl; private Author(String name, String url, String iconUrl) { this.name = name; this.url = url; this.iconUrl = iconUrl; } private String getName() { return this.name; } private String getUrl() { return this.url; } private String getIconUrl() { return this.iconUrl; } } private class Field { private String name; private boolean isInline() { return this.inline; } private String value; private boolean inline; private Field(String name, String value, boolean inline) { this.name = name;
/*     */       this.value = value;
/*     */       this.inline = inline; } private String getName() {
/*     */       return this.name;
/*     */     } private String getValue() {
/*     */       return this.value;
/*     */     } }
/* 345 */    private class JSONObject { private final HashMap<String, Object> map = new HashMap<>();
/*     */     
/*     */     void put(String key, Object value) {
/* 348 */       if (value != null) {
/* 349 */         this.map.put(key, value);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 355 */       StringBuilder builder = new StringBuilder();
/* 356 */       Set<Map.Entry<String, Object>> entrySet = this.map.entrySet();
/* 357 */       builder.append("{");
/*     */       
/* 359 */       int i = 0;
/* 360 */       for (Map.Entry<String, Object> entry : entrySet) {
/* 361 */         Object val = entry.getValue();
/* 362 */         builder.append(quote(entry.getKey())).append(":");
/*     */         
/* 364 */         if (val instanceof String) {
/* 365 */           builder.append(quote(String.valueOf(val)));
/* 366 */         } else if (val instanceof Integer) {
/* 367 */           builder.append(Integer.valueOf(String.valueOf(val)));
/* 368 */         } else if (val instanceof Boolean) {
/* 369 */           builder.append(val);
/* 370 */         } else if (val instanceof JSONObject) {
/* 371 */           builder.append(val.toString());
/* 372 */         } else if (val.getClass().isArray()) {
/* 373 */           builder.append("[");
/* 374 */           int len = Array.getLength(val);
/* 375 */           for (int j = 0; j < len; j++) {
/* 376 */             builder.append(Array.get(val, j).toString()).append((j != len - 1) ? "," : "");
/*     */           }
/* 378 */           builder.append("]");
/*     */         } 
/*     */         
/* 381 */         builder.append((++i == entrySet.size()) ? "}" : ",");
/*     */       } 
/*     */       
/* 384 */       return builder.toString();
/*     */     }
/*     */     
/*     */     private String quote(String string) {
/* 388 */       return "\"" + string + "\"";
/*     */     }
/*     */     
/*     */     private JSONObject() {} }
/*     */ 
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclien\\utils\DiscordWebhook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */